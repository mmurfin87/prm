package com.yourcompany.personalcrm.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import com.yourcompany.personalcrm.login.User;
import com.yourcompany.personalcrm.login.UserDetailsServiceRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class DatabaseSecurityContextRepository implements SecurityContextRepository
{
    public void invalidateUserToken(@NonNull final String userId, @NonNull final String token)
    {
        log.info("Invalidating token: {} | {}", userId, token);
        repository.deleteUserToken(userId, token);
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder)
    {
        final SecurityContext sc = Optional.ofNullable(requestResponseHolder.getRequest().getRequestedSessionId())
            .map(repository::findUserByToken)
            .map(user -> (SecurityContext)new SecurityContextImpl(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())))
            .orElseGet(SecurityContextHolder::createEmptyContext);
        log.info("Loaded: {}", Optional.ofNullable(sc.getAuthentication()).map(Authentication::getPrincipal).orElse("NULL"));
        return sc;
    }

    @Override
    public void saveContext(@NonNull final SecurityContext context, HttpServletRequest request, HttpServletResponse response)
    {
        final String sessionId = request.getRequestedSessionId();
        Optional.ofNullable(context.getAuthentication())
            .map(Authentication::getPrincipal)
            .filter(User.class::isInstance)
            .filter(user -> repository.findUserByToken(sessionId) == null)
            .ifPresent(user -> repository.saveToken(((User)user).username, sessionId, "SESSION", Instant.now().plus(1, ChronoUnit.DAYS)));
    }

    @Override
    public boolean containsContext(HttpServletRequest request)
    {
        final String sessionId = request.getRequestedSessionId();
        final Boolean tokenValidity = sessionId == null ? null : repository.checkTokenValidity(sessionId);
        log.info("Checking Session Validity: {} | {}", sessionId, tokenValidity);
        return Boolean.TRUE.equals(tokenValidity);
    }

    @NonNull
    private final UserDetailsServiceRepository repository;
}
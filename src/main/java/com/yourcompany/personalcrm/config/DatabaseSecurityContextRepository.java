package com.yourcompany.personalcrm.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

@Component
@AllArgsConstructor
public class DatabaseSecurityContextRepository implements SecurityContextRepository
{
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder)
    {
        return Optional.ofNullable(requestResponseHolder.getRequest().getRequestedSessionId())
            .map(repository::findUserByToken)
            .map(user -> (SecurityContext)new SecurityContextImpl(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())))
            .orElseGet(SecurityContextHolder::createEmptyContext);
    }

    @Override
    public void saveContext(@NonNull final SecurityContext context, HttpServletRequest request, HttpServletResponse response)
    {
        final String sessionId = request.getRequestedSessionId();
        final Object principal = context.getAuthentication().getPrincipal();
        if (sessionId != null && principal instanceof User user)
            repository.saveToken(user.username, sessionId, "SESSION", Instant.now().plus(1, ChronoUnit.DAYS));
    }

    @Override
    public boolean containsContext(HttpServletRequest request)
    {
        String sessionId = request.getRequestedSessionId();
        return sessionId != null && repository.findValidTokenByToken(sessionId) != null;
    }

    @NonNull
    private final UserDetailsServiceRepository repository;
}
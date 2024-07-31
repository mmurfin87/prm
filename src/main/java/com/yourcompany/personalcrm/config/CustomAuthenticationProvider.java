package com.yourcompany.personalcrm.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.yourcompany.personalcrm.login.User;
import com.yourcompany.personalcrm.login.UserDetailsServiceRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        log.info("Authenticating: {}", username);
        
        final User user = userRepo.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Invalid username or password");
        log.info("Login successful. Setting user: {}", user);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());    
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    @NonNull
    private final UserDetailsServiceRepository userRepo;
    @NonNull
    private final PasswordEncoder passwordEncoder;
}

package com.yourcompany.personalcrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig
{
    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .headers(hdrs -> hdrs.frameOptions(foc -> foc.disable()))
            .authorizeHttpRequests(authorize -> authorize
                    .anyRequest().permitAll()
                )
            .build();
    }
    */

    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull final HttpSecurity http, @NonNull final CustomAuthenticationProvider authenticationProvider, @NonNull final TokenAuthenticationFilter tokenAuthenticationFilter, @NonNull final DatabaseSecurityContextRepository securityContextRepository) throws Exception
    {
        return http
            .csrf(CsrfConfigurer::disable)
            .headers(hdrs -> hdrs.frameOptions(FrameOptionsConfig::sameOrigin))
            .securityContext(scc -> scc.securityContextRepository(securityContextRepository))
            //.authenticationProvider(authenticationProvider)
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)    
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home", "/signup", "/h2-console/**", "/js/**", "/css/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                //.loginPage("/login")
                .permitAll()
            )
            .logout((logout) -> logout.permitAll())
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    public AuthenticationManager authenticationManager(@NonNull final HttpSecurity http, @NonNull final CustomAuthenticationProvider authenticationProvider) throws Exception
    {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(authenticationProvider)
            .build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler()
    {
        return (request, response, authentication) ->
        {
            
        };
    }
}
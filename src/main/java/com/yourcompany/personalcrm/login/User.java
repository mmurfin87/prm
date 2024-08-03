package com.yourcompany.personalcrm.login;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class User implements UserDetails
{
    @NonNull
    public final String id;
    public final String username;
    public final String email;
    @NonNull
    public final String passwordHash;

    public static String identify()
    {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user ? user.id : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return BASIC_AUTHORITY_LIST;
    }

    @Override
    public String getPassword()
    {
        return passwordHash;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    private static final GrantedAuthority USER_AUTHORITY = new SimpleGrantedAuthority("USER");
    private static final List<? extends GrantedAuthority> BASIC_AUTHORITY_LIST = List.of(USER_AUTHORITY);
}
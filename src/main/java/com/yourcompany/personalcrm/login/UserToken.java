package com.yourcompany.personalcrm.login;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class UserToken
{
    @NonNull
    public final String id;
    @NonNull
    public final String userId;
    @NonNull
    public final String token;
    @NonNull
    public final String tokenType;
    @NonNull
    public final Instant expiration;
}

package com.yourcompany.personalcrm.signup;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class SignUpRequest
{
    @NonNull
    public final String username;
    @NonNull
    public final String email;
    @NonNull
    @ToString.Exclude
    public final String password;
    @NonNull
    @ToString.Exclude
    public final String confirmPassword;
}
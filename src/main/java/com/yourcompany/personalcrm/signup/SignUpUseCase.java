package com.yourcompany.personalcrm.signup;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class SignUpUseCase
{
    public void execute(@NonNull final SignUpRequest request)
    {
        if (!Objects.equals(request.password, request.confirmPassword))
            throw new IllegalArgumentException("Passwords do not match");
        if (repo.findByUsernameOrEmail(request.username, request.email).size() > 0)
            throw new IllegalArgumentException("Username or email already exists");
        repo.insertUser(request.username, request.email, passwordEncoder.encode(request.password));
    }

    @NonNull
    private final SignUpRepository repo;
    @NonNull
    private final PasswordEncoder passwordEncoder;
}

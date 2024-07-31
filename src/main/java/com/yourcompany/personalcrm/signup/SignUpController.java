package com.yourcompany.personalcrm.signup;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.config.PostRedirectGet;

import org.springframework.web.bind.annotation.ModelAttribute;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
public class SignUpController
{
    @GetMapping("/signup")
    public Object showSignupForm()
    {
        return new Object();
    }

    @PostRedirectGet("/")
    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void registerUser(@NonNull @ModelAttribute SignUpRequest request)
    {
        useCase.execute(request);
    }
    
    @NonNull
    private final SignUpUseCase useCase;
}
package com.yourcompany.personalcrm.contacts.addcontact;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.login.User;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AddContactUseCase
{
    public AddContactResponse execute(AddContactRequest request)
    {
        log.info("CurrentUser: {}", User.identify());
        final String gender = request.gender == null && request.gender.isBlank() ? null : request.gender;
        long generatedId = repository.insert(
            User.identify(),
            request.firstName,
            request.lastName,
            request.birthdate,
            gender,
            request.email,
            request.phone,
            request.company
        );

        return new AddContactResponse(String.valueOf(generatedId), "Contact added successfully");
    }

    @NonNull
    private final AddContactRepository repository;
}
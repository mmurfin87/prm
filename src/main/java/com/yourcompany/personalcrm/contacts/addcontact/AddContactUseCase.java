package com.yourcompany.personalcrm.contacts.addcontact;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class AddContactUseCase
{
    public AddContactResponse execute(AddContactRequest request)
    {
        final String gender = request.gender == null && request.gender.isBlank() ? null : request.gender;
        long generatedId = repository.insert(
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
package com.yourcompany.personalcrm.contacts.addcontact;

import org.springframework.stereotype.Service;

@Service
public class AddContactUseCase
{
    private final AddContactRepository repository;

    public AddContactUseCase(AddContactRepository repository)
    {
        this.repository = repository;
    }

    public AddContactResponse execute(AddContactRequest request)
    {
        long generatedId = repository.insert(
            request.firstName,
            request.lastName,
            request.email,
            request.phone,
            request.company
        );

        return new AddContactResponse(String.valueOf(generatedId), "Contact added successfully");
    }
}
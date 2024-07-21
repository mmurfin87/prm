package com.yourcompany.personalcrm.contacts.updatecontact;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class UpdateContactUseCase
{
    private final UpdateContactRepository repository;

    public void execute(@NonNull final String id, @NonNull final UpdateContactRequest request)
    {
        int updatedRows = repository.updateContact(id, request);
        if (updatedRows == 0)
            throw new IllegalArgumentException("Contact not found with id: " + id);
    }
}
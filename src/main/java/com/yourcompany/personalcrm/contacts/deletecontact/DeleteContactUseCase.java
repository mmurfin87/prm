package com.yourcompany.personalcrm.contacts.deletecontact;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class DeleteContactUseCase
{
    @NonNull
    private final DeleteContactRepository repository;

    public void execute(String contactId)
    {
        if (!repository.deleteContact(contactId))
            throw new IllegalArgumentException("No such contact");
    }
}
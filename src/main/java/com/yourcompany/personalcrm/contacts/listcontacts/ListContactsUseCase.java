package com.yourcompany.personalcrm.contacts.listcontacts;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ListContactsUseCase
{
    private final ListContactsRepository repository;

    public ListContactsUseCase(ListContactsRepository repository)
    {
        this.repository = repository;
    }

    public List<ContactSummary> execute()
    {
        return repository.listContacts();
    }
}
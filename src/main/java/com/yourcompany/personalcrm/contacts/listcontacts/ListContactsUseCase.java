package com.yourcompany.personalcrm.contacts.listcontacts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.login.User;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ListContactsUseCase
{
    public List<ContactSummary> execute()
    {
        log.info("CurrentUser: {}", User.identify());
        return repository.listContacts(User.identify());
    }
    
    @NonNull
    private final ListContactsRepository repository;
}
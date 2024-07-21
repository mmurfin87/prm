package com.yourcompany.personalcrm.contacts.listcontacts;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("contacts")
@Tag(name = "Contact", description = "Contact management APIs")
public class ListContactsController
{
    @NonNull
    private final ListContactsUseCase listContactsUseCase;

    @GetMapping
    @Operation(summary = "List all contacts", description = "Retrieves a list of all contacts in the system")
    public List<ContactSummary> listContacts()
    {
        return listContactsUseCase.execute();
    }
}
package com.yourcompany.personalcrm.contacts.updatecontact;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.yourcompany.personalcrm.config.PostRedirectGet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;

@RestController
@RequestMapping("/contacts")
@Tag(name = "Contact", description = "Contact management APIs")
public class UpdateContactController
{
    private final UpdateContactUseCase updateContactUseCase;

    public UpdateContactController(UpdateContactUseCase updateContactUseCase)
    {
        this.updateContactUseCase = updateContactUseCase;
    }

    @PostRedirectGet
    @PostMapping(path = "{id}/edit", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Update contact", description = "Updates an existing contact's information")
    public void updateContact(@PathVariable @NonNull final String id, @NonNull final UpdateContactRequest request)
    {
        try
        {
            updateContactUseCase.execute(id, request);
        }
        catch (IllegalArgumentException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", e);
        }
    }
}
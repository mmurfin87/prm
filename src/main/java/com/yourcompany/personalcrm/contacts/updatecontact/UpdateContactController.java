package com.yourcompany.personalcrm.contacts.updatecontact;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.config.PostRedirectGet;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/contacts")
@Tag(name = "Contact", description = "Contact management APIs")
public class UpdateContactController
{
    @GetMapping(path = "{id}/edit")
    @Operation(summary = "Prepare Update Contact Form")
    public UpdateContactForm prepareUpdateContactForm(@PathVariable @NonNull final String id)
    {
        return updateContactUseCase.prepareForm(id);
    }

    @PostMapping(path = "{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update contact", description = "Updates an existing contact's information",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateContactRequest.class)),
                @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation=UpdateContactRequest.class))
            }
        ))
    public void updateContact(@PathVariable @NonNull final String id, @NonNull final UpdateContactRequest request)
    {
        updateContactUseCase.execute(id, request);
    }

    @Hidden
    @PostRedirectGet
    @PostMapping(path = "{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateContactForm(@PathVariable @NonNull final String id, @ModelAttribute @NonNull final UpdateContactRequest request)
    {
        updateContactUseCase.execute(id, request);
    }
    
    @NonNull
    private final UpdateContactUseCase updateContactUseCase;
}
package com.yourcompany.personalcrm.contacts.addcontact;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.config.PostRedirectGet;
import com.yourcompany.personalcrm.config.View;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("contacts")
@Tag(name = "Contact", description = "Contact management APIs")
public class AddContactController
{
    @NonNull
    private final AddContactUseCase addContactUseCase;

    @Hidden
    @GetMapping("new")
    public Object prepareAddContact()
    {
        return new Object();
    }

    @PostRedirectGet
    @Operation(summary = "Add a new contact", description = "Adds a new contact to the system", 
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddContactRequest.class)),
                @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation=AddContactRequest.class))
            }
        ))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddContactResponse addContactJson(@NonNull @RequestBody final AddContactRequest request)
    {
        return addContactUseCase.execute(request);
    }

    @Hidden
    @PostRedirectGet
    @View("contacts/created")
    @Operation(summary = "Add a new contact", description = "Adds a new contact to the system")
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public AddContactResponse addContactForm(@NonNull @ModelAttribute final AddContactRequest request)
    {
        return addContactUseCase.execute(request);
    }
}
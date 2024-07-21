package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@RequestMapping("contacts")
@AllArgsConstructor
@Tag(name = "Contact", description = "Contact management APIs")
public class InspectContactDetailsController
{
    @NonNull
    private final InspectContactDetailsUseCase inspectContactDetailsUseCase;

    @GetMapping({ "{id}", "{id}/edit" })
    @Operation(summary = "Inspect contact details", description = "Retrieves detailed information for a specific contact")
    public ContactDetailsWithInteractions inspectContactDetails(@PathVariable String id)
    {
        return inspectContactDetailsUseCase.execute(id);
    }
}
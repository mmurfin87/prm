package com.yourcompany.personalcrm.contacts.deletecontact;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/contacts")
@Tag(name = "Contact", description = "Contact management APIs")
public class DeleteContactController
{
    @NonNull
    private final DeleteContactUseCase deleteContactUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete contact", 
               description = "Deletes an existing contact")
    @ApiResponse(responseCode = "200", description = "Contact deleted successfully")
    @ApiResponse(responseCode = "404", description = "Contact not found")
    public void deleteContact(
        @Parameter(description = "ID of the contact to delete") 
        @PathVariable String id)
    {
        deleteContactUseCase.execute(id);
    }
}
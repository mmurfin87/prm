package com.yourcompany.personalcrm.relationships.deleterelationship;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/relationships")
@Tag(name = "Relationship", description = "Relationship management APIs")
public class DeleteRelationshipController
{
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete relationship", 
               description = "Deletes an existing relationship")
    @ApiResponse(responseCode = "204", description = "Relationship deleted successfully")
    @ApiResponse(responseCode = "404", description = "Relationship not found")
    public void deleteRelationship(@PathVariable String id)
    {
        useCase.execute(id);
    }


    @NonNull
    private final DeleteRelationshipUseCase useCase;
}
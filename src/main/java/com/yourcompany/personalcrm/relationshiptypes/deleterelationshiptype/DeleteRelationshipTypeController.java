package com.yourcompany.personalcrm.relationshiptypes.deleterelationshiptype;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/relationship-types")
@Tag(name = "RelationshipType", description = "Relationship Type management APIs")
public class DeleteRelationshipTypeController
{
    @NonNull
    private final DeleteRelationshipTypeUseCase deleteRelationshipTypeUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete relationship type", 
               description = "Deletes an existing relationship type")
    @ApiResponse(responseCode = "204", description = "Relationship type deleted successfully")
    @ApiResponse(responseCode = "404", description = "Relationship type not found")
    public void deleteRelationshipType(@PathVariable String id)
    {
        deleteRelationshipTypeUseCase.execute(id);
    }
}
package com.yourcompany.personalcrm.interactiontypes.deleteinteractiontype;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/interaction-types")
@Tag(name = "InteractionType", description = "Interaction Type management APIs")
public class DeleteInteractionTypeController
{
    @NonNull
    private final DeleteInteractionTypeUseCase deleteInteractionTypeUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete interaction type", 
               description = "Deletes an existing interaction type")
    @ApiResponse(responseCode = "204", description = "Interaction type deleted successfully")
    @ApiResponse(responseCode = "404", description = "Interaction type not found")
    public void deleteInteractionType(@PathVariable String id)
    {
        deleteInteractionTypeUseCase.execute(id);
    }
}
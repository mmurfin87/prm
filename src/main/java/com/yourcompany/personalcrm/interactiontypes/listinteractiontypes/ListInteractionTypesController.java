package com.yourcompany.personalcrm.interactiontypes.listinteractiontypes;

import org.springframework.web.bind.annotation.*;

import com.yourcompany.personalcrm.interactiontypes.InteractionType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/interaction-types")
@Tag(name = "InteractionType", description = "Interaction Type management APIs")
public class ListInteractionTypesController
{
    @GetMapping
    @Operation(summary = "List all interaction types", description = "Retrieves a list of all interaction types")
    public List<InteractionType> listInteractionTypes()
    {
        return listInteractionTypesUseCase.execute();
    }

    @NonNull
    private final ListInteractionTypesUseCase listInteractionTypesUseCase;
}
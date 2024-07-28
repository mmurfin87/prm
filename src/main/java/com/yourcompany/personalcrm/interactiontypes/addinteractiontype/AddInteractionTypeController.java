package com.yourcompany.personalcrm.interactiontypes.addinteractiontype;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.yourcompany.personalcrm.config.PostRedirectGet;
import com.yourcompany.personalcrm.interactiontypes.InteractionType;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("interaction-types")
@Tag(name = "InteractionType", description = "Interaction Type management APIs")
public class AddInteractionTypeController
{
    @NonNull
    private final AddInteractionTypeUseCase addInteractionTypeUseCase;

    @Hidden
    @GetMapping("new")
    public Object prepareForm()
    {
        return new Object();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new interaction type", description = "Adds a new interaction type to the system",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddInteractionTypeRequest.class)),
                @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation = AddInteractionTypeRequest.class))
        }))
    public InteractionType addInteractionType(@NonNull @RequestBody final AddInteractionTypeRequest request)
    {
        return addInteractionTypeUseCase.execute(request.name);
    }

    @Hidden
    @PostRedirectGet("interaction-types")
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public InteractionType addInteractionTypeForm(@NonNull @ModelAttribute final AddInteractionTypeRequest request)
    {
        return addInteractionTypeUseCase.execute(request.name);
    }
}
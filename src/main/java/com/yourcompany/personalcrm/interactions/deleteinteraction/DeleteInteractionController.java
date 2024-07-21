package com.yourcompany.personalcrm.interactions.deleteinteraction;

import org.springframework.web.bind.annotation.*;

import com.yourcompany.personalcrm.config.PostRedirectGet;

import org.springframework.http.MediaType;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/interactions")
@Tag(name = "Interaction", description = "Interaction management APIs")
public class DeleteInteractionController
{
    @NonNull
    private final DeleteInteractionUseCase deleteInteractionUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete interaction", description = "Deletes an existing interaction")
    @ApiResponse(responseCode = "200", description = "Interaction deleted successfully")
    @ApiResponse(responseCode = "404", description = "Interaction not found")
    public void deleteInteraction(@PathVariable String id)
    {
        deleteInteractionUseCase.execute(id);
    }

    @Hidden
    @PostRedirectGet(toReferrer = true)
    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void deleteInteractionForm(@RequestParam String id)
    {
        deleteInteractionUseCase.execute(id);
    }
}
package com.yourcompany.personalcrm.relationships.inspectcontactrelationships;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/relationships")
@Tag(name = "Relationship", description = "Relationship management APIs")
public class InspectContactRelationshipController
{
    @NonNull
    private final InspectContactRelationshipsUseCase inspectContactRelationshipsUseCase;

    @GetMapping("/contacts/{contactId}")
    @Operation(summary = "Inspect contact relationships", description = "Retrieves all relationships associated with a specific contact")
    public List<RelationshipSummary> inspectContactRelationships(@PathVariable String contactId)
    {
        return inspectContactRelationshipsUseCase.execute(contactId);
    }
}
package com.yourcompany.personalcrm.relationships.listrelationships;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/relationships")
@Tag(name = "Relationship", description = "Relationship management APIs")
public class ListRelationshipsController
{
    @NonNull
    private final ListRelationshipsUseCase listRelationshipsUseCase;

    @GetMapping
    @Operation(summary = "List all relationships", description = "Retrieves a list of all relationship")
    public List<Relationship> listRelationships()
    {
        return listRelationshipsUseCase.execute();
    }
}
package com.yourcompany.personalcrm.relationshiptypes.listrelationshiptypes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/relationship-types")
@Tag(name = "RelationshipType", description = "Relationship Type management APIs")
public class ListRelationshipTypesController
{
    @NonNull
    private final ListRelationshipTypesUseCase listRelationshipTypesUseCase;

    @GetMapping
    @Operation(summary = "List all relationship types", description = "Retrieves a list of all relationship types")
    public List<RelationshipType> listRelationshipTypes()
    {
        return listRelationshipTypesUseCase.execute();
    }
}
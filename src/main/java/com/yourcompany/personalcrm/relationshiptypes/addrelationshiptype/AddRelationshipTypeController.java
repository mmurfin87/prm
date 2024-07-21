package com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.config.PostRedirectGet;
import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("relationship-types")
@Tag(name = "RelationshipType", description = "Relationship Type management APIs")
public class AddRelationshipTypeController
{
    @NonNull
    private final AddRelationshipTypeUseCase addRelationshipTypeUseCase;

    @Hidden
    @GetMapping("new")
    public Object prepareForm()
    {
        return new Object();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new relationship type", description = "Adds a new relationship type with configurable role names",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddRelationshipTypeRequest.class)),
            @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation = AddRelationshipTypeRequest.class), encoding = @Encoding(explode = true))
        }))
    public RelationshipType addRelationshipTypeJson(@NonNull @RequestBody final AddRelationshipTypeRequest request)
    {
        return addRelationshipTypeUseCase.execute(request);
    }

    @Hidden
    @PostRedirectGet("contacts")
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RelationshipType addRelationshipTypeForm(@NonNull @ModelAttribute final AddRelationshipTypeRequest request)
    {
        return addRelationshipTypeUseCase.execute(request);
    }
}
package com.yourcompany.personalcrm.relationships.addrelationship;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.config.PostRedirectGet;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/relationships")
@Tag(name = "Relationship", description = "Relationship management APIs")
public class AddRelationshipController
{
    @GetMapping("new")
    @Operation(summary = "Prepare a new relationship", description = "Prepare the form with data needed for adding a new relationship")
    public AddRelationshipForm prepareRelationshipForm(@RequestParam(name = "for-contact-id", required = false) String forContactId)
    {
        return addRelationshipUseCase.prepareAddRelationshipForm(forContactId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new relationship", description = "Adds a new relationship between two contacts",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddRelationshipRequest.class)),
            @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation = AddRelationshipRequest.class))
        }))
    public String addRelationship(@NonNull @RequestBody final AddRelationshipRequest request)
    {
        return addRelationshipUseCase.execute(request);
    }

    @Hidden
    @PostRedirectGet("contacts")
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addRelationshipForm(@NonNull @ModelAttribute final AddRelationshipRequest request)
    {
        return addRelationshipUseCase.execute(request);
    }
    
    @NonNull
    private final AddRelationshipUseCase addRelationshipUseCase;
}
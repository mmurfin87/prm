package com.yourcompany.personalcrm.interactions.addinteraction;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.config.PostRedirectGet;
import com.yourcompany.personalcrm.contacts.listcontacts.ContactSummary;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("interactions")
@Tag(name = "Interaction", description = "Interaction management APIs")
public class AddInteractionController
{
    @NonNull
    private final AddInteractionUseCase addInteractionUseCase;

    @GetMapping("new")
    @Operation(summary = "Prepare to add a new interaction", description = "Fetch data necessary to add a new interaction, such as the list of contacts available to interact with")
    public Set<Map.Entry<String, String>> showAddInteractionForm(Model model)
    {
        return addInteractionUseCase.identifyAvailableContacts().entrySet();
    }

    @PostRedirectGet
    @Operation(summary = "Add a new interaction", description = "Adds a new interaction to the system", 
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddInteractionRequest.class)),
                @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation=AddInteractionRequest.class))
            }
        ))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddInteractionResponse addInteractionJson(@NonNull @RequestBody final AddInteractionRequest request)
    {
        return addInteractionUseCase.execute(request);
    }

    @Hidden
    @PostRedirectGet("contacts")
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public AddInteractionResponse addInteractionForm(@NonNull @ModelAttribute final AddInteractionRequest request)
    {
        return addInteractionUseCase.execute(request);
    }
}
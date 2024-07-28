package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.yourcompany.personalcrm.config.PostRedirectGet;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("field-types")
@Tag(name = "FieldType", description = "Field Type management APIs")
public class AddCustomFieldTypeController
{
    @NonNull
    private final AddCustomFieldTypeUseCase addCustomFieldTypeUseCase;

    @GetMapping("new")
    public AddCustomFieldTypeForm prepareForm()
    {
        return addCustomFieldTypeUseCase.prepareForm();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new custom field type", description = "Adds a new custom field type to the system, including complex object types with custom fields",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddCustomFieldTypeRequest.class)),
            @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE, schema = @Schema(implementation = AddCustomFieldTypeRequest.class))
        }))
    public AddCustomFieldTypeResponse addCustomFieldType(@NonNull @RequestBody final AddCustomFieldTypeRequest request)
    {
        return addCustomFieldTypeUseCase.execute(request);
    }

    @Hidden
    @PostRedirectGet
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public AddCustomFieldTypeResponse addCustomFieldTypeForm(@NonNull @ModelAttribute final AddCustomFieldTypeRequest request)
    {
        log.info("Form Request: {}", request);
        return addCustomFieldTypeUseCase.execute(request);
    }
}
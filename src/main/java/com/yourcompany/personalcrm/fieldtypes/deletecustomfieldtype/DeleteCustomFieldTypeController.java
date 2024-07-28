package com.yourcompany.personalcrm.fieldtypes.deletecustomfieldtype;

import org.springframework.web.bind.annotation.*;

import com.yourcompany.personalcrm.config.PostRedirectGet;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/field-types")
@Tag(name = "FieldType", description = "Field Type management APIs")
public class DeleteCustomFieldTypeController
{
    @NonNull
    private final DeleteCustomFieldTypeUseCase deleteCustomFieldTypeUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete custom field type", 
               description = "Deletes an existing custom field type")
    @ApiResponse(responseCode = "204", description = "Custom field type deleted successfully")
    @ApiResponse(responseCode = "404", description = "Custom field type not found")
    public void deleteCustomFieldType(@PathVariable String id)
    {
        deleteCustomFieldTypeUseCase.execute(id);
    }

    @Hidden
    @PostMapping("{id}/delete")
    @PostRedirectGet(toReferrer = true)
    public void deleteCustomFieldTypePost(@PathVariable String id)
    {
        deleteCustomFieldTypeUseCase.execute(id);
    }
}
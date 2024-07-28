package com.yourcompany.personalcrm.fieldtypes.listfieldtypes;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/field-types")
@Tag(name = "FieldType", description = "Field Type management APIs")
public class ListFieldTypesController
{
    @NonNull
    private final ListFieldTypesUseCase listFieldTypesUseCase;

    @GetMapping
    @Operation(summary = "List all field types", description = "Retrieves a list of all field types")
    public List<FieldTypeSummary> listFieldTypes()
    {
        return listFieldTypesUseCase.execute();
    }
}
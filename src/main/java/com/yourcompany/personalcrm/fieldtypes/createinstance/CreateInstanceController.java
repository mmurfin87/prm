package com.yourcompany.personalcrm.fieldtypes.createinstance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.personalcrm.config.PostRedirectGet;
import com.yourcompany.personalcrm.config.View;
import com.yourcompany.personalcrm.fieldtypes.listfieldtypes.FieldTypeSummary;
import com.yourcompany.personalcrm.fieldtypes.listfieldtypes.ListFieldTypesUseCase;
import com.yourcompany.personalcrm.util.FormOption;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("field-types")
public class CreateInstanceController {

    @NonNull
    private final CreateInstanceUseCase createCustomObjectUseCase;
    @NonNull
    private final ListFieldTypesUseCase listFieldTypesUseCase;
    @NonNull
    private final ObjectMapper om;

    @View("/field-types/id/new")
    @GetMapping({"new-instance", "{id}/new"})
    public CreateInstanceForm showCreateCustomObjectForm(@PathVariable(required = false) String id) throws JsonProcessingException
    {
        final List<FieldTypeSummary> fieldTypes = listFieldTypesUseCase.execute();
        List<FormOption> customTypes = fieldTypes.stream()
                .filter(type -> "user".equals(type.pkg))
                .map(fts -> new FormOption(fts.id, fts.name, fts.id.equalsIgnoreCase(id)))
                .collect(Collectors.toList());
        final Map<String, FieldTypeSummary> fieldTypeMap = fieldTypes.stream()
            .collect(Collectors.toMap(fts -> fts.id, Function.identity()));
        return new CreateInstanceForm(
            id,
            Optional.ofNullable(id).map(fieldTypeMap::get).map(ftm -> ftm.name).orElse(null),
            customTypes,
            om.writeValueAsString(fieldTypeMap)
        );
    }

    @PostMapping("{id}")
    @PostRedirectGet("field-types")
    public void createCustomObject(@NonNull @PathVariable final String id, @NonNull @ModelAttribute final CreateInstanceRequest request)
    {
        log.info("Processing New Instance Request ({}): {}", id, request);
        createCustomObjectUseCase.execute(request);
    }
}
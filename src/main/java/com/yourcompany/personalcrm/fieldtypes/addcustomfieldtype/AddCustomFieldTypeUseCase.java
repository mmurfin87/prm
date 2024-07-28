package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.personalcrm.util.FormOption;

@Service
@AllArgsConstructor
public class AddCustomFieldTypeUseCase
{
    @SneakyThrows
    public AddCustomFieldTypeForm prepareForm()
    {
        final List<FormOption> baseTypeOptions = repository.getFieldTypesByPackage("core").entrySet().stream()
            .map(e -> new FormOption(e.getKey(), e.getValue(), false))
            .toList();
        return new AddCustomFieldTypeForm(
            baseTypeOptions,
            om.writeValueAsString(baseTypeOptions)
        );
    }

    @Transactional
    public AddCustomFieldTypeResponse execute(@NonNull final AddCustomFieldTypeRequest request)
    {
        String objectTypeId = repository.getFieldTypeIdByName("Object");
        
        // Create the new custom field type
        String newCustomTypeId = repository.addCustomFieldType(request.name, "user", request.description, request.baseTypeId);
        
        // If this is an object type and has fields defined
        if (objectTypeId.equals(request.baseTypeId) && request.fields != null && !request.fields.isEmpty())
        {
            for (AddCustomFieldType field : request.fields)
            {
                // newCustomTypeId is the parent_field_type_id (the object type we're creating)
                // field.fieldTypeId is the field_type_id (the type of this specific field)
                repository.addDynamicField(newCustomTypeId, field.name, field.fieldTypeId, field.isRequired, field.defaultValue);
            }
        }

        return repository.getCustomFieldTypeById(newCustomTypeId);
    }

    @NonNull
    private final ObjectMapper om;
    @NonNull
    private final AddCustomFieldTypeRepository repository;
}
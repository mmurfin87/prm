package com.yourcompany.personalcrm.fieldtypes.deletecustomfieldtype;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DeleteCustomFieldTypeUseCase
{
    @Transactional
    public void execute(@NonNull final String id)
    {
        List<String> typeHierarchy = repository.getFieldTypeHierarchy(id);
        
        for (String typeId : typeHierarchy)
        {
            if (repository.countDependentTypes(typeId) > 0)
                throw new IllegalStateException("Cannot delete field type " + typeId + " as it has dependent types");
            if (repository.countDependentFields(typeId) > 0)
                throw new IllegalStateException("Cannot delete field type " + typeId + " as it is used in dynamic fields");
            
            repository.deleteDynamicFields(typeId);
            int deletedCount = repository.deleteCustomFieldType(typeId);
            if (deletedCount < 1)
                throw new IllegalStateException("Cannot delete field type " + typeId + " as it is not a custom type");
        }
    }

    @NonNull
    private final DeleteCustomFieldTypeRepository repository;
}
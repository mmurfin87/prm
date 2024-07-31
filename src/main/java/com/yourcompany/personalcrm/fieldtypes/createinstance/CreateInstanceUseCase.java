package com.yourcompany.personalcrm.fieldtypes.createinstance;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateInstanceUseCase {
    @NonNull
    private final CreateInstanceRepository repository;

    @Transactional
    public String execute(@NonNull final CreateInstanceRequest request) {
        String entityId = repository.createDynamicEntity(request.getFieldTypeId());
        
        for (CreateInstanceRequest.FieldValue fieldValue : request.getFieldValues()) {
            repository.addDynamicFieldValue(entityId, fieldValue.getFieldId(), fieldValue.getValue());
        }
        
        return entityId;
    }
}
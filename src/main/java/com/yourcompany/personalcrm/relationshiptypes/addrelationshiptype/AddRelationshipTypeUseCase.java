package com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class AddRelationshipTypeUseCase
{
    @NonNull
    private final AddRelationshipTypeRepository repository;

    public RelationshipType execute(AddRelationshipTypeRequest request)
    {
        final List<String> errors = new ArrayList<>(5);
        validateNotBlank(errors, "name", request.name);
        validateNotBlank(errors, "originmaleName", request.originMaleName);
        validateNotBlank(errors, "originfemaleName", request.originFemaleName);
        validateNotBlank(errors, "targetmaleName", request.targetMaleName);
        validateNotBlank(errors, "targetfemaleName", request.targetFemaleName);
        if (!errors.isEmpty())
            throw new IllegalArgumentException("Invalid request: " + String.join(" | ", errors));
        
        final String id = repository.add(request);
        
        return new RelationshipType(id, request.name, request.originMaleName, request.originFemaleName, request.targetMaleName, request.targetFemaleName);
    }

    private void validateNotBlank(List<String> errors, String key, String value)
    {
        if (value.isBlank())
            errors.add(key + " cannot be empty");
    }
}
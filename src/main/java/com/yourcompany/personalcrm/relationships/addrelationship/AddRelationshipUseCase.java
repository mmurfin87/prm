package com.yourcompany.personalcrm.relationships.addrelationship;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.NotFoundException;
import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;
import com.yourcompany.personalcrm.relationshiptypes.listrelationshiptypes.ListRelationshipTypesUseCase;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class AddRelationshipUseCase
{
    public AddRelationshipForm prepareAddRelationshipForm(final String forContactId)
    {
        final Map<String, String> contacts = repository.listContactIdsAndNames();
        if (forContactId != null && contacts.get(forContactId) == null)
            throw new NotFoundException("Couldn't find requested contact id");
        final List<AddRelationshipContactOption> targetOptions = contacts.entrySet().stream()
            .filter(e -> !e.getKey().equalsIgnoreCase(forContactId))
            .map(e -> new AddRelationshipContactOption(e.getKey(), e.getValue(), false))
            .toList();
        final List<AddRelationshipContactOption> originOptions = forContactId == null || forContactId.isBlank()
            ? targetOptions
            : List.of(new AddRelationshipContactOption(forContactId, contacts.get(forContactId), true));
        
        final List<RelationshipType> relationshipTypes = listRelationshipTypesUseCase.execute();
        return new AddRelationshipForm(originOptions, targetOptions, relationshipTypes);
    }

    public String execute(AddRelationshipRequest request)
    {
        final RelationshipType relationshipType = repository.inspectRelationshipType(request.relationshipTypeId);
        if (relationshipType == null)
            throw new IllegalArgumentException("No such relationship");
        final String relationshipId = repository.addRelationship(request.originContactId, request.targetContactId, request.relationshipTypeId, request.notes);
        if (relationshipType.counterpartId != null)
            repository.addRelationship(request.targetContactId, request.originContactId, relationshipType.counterpartId, null);
        return relationshipId;
    }

    @NonNull
    private final AddRelationshipRepository repository;
    @NonNull
    private final ListRelationshipTypesUseCase listRelationshipTypesUseCase;
}
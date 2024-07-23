package com.yourcompany.personalcrm.relationships.inspectcontactrelationships;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class InspectContactRelationshipsUseCase
{
    @NonNull
    private final InspectContactRelationshipRepository repository;

    public List<RelationshipSummary> execute(String contactId)
    {
        return repository.getRelationshipsForContact(contactId);
    }
}
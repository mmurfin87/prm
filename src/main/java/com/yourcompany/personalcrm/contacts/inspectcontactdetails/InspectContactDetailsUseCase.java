package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.NotFoundException;
import com.yourcompany.personalcrm.relationships.inspectcontactrelationships.InspectContactRelationshipsUseCase;
import com.yourcompany.personalcrm.relationships.inspectcontactrelationships.RelationshipSummary;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InspectContactDetailsUseCase
{
    public CompleteContactDetails execute(String id)
    {
        final ContactDetails contactDetails = repository.inspectContactDetailsById(id);
        if (contactDetails == null)
            throw new NotFoundException();
        final List<InteractionSummary> interactionSummaries = repository.inspectContactInteractions(id);
        final List<RelationshipSummary> relationshipSummaries = inspectContactRelationshipsUseCase.execute(id);
        return new CompleteContactDetails(contactDetails, interactionSummaries, relationshipSummaries);
    }
    
    private final InspectContactDetailsRepository repository;
    private final InspectContactRelationshipsUseCase inspectContactRelationshipsUseCase;
}
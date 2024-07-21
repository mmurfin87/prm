package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.NotFoundException;

@Service
public class InspectContactDetailsUseCase
{
    private final InspectContactDetailsRepository repository;

    public InspectContactDetailsUseCase(InspectContactDetailsRepository repository)
    {
        this.repository = repository;
    }

    public ContactDetailsWithInteractions execute(String id)
    {
        final long contactId = Long.parseLong(id);
        final ContactDetails contactDetails = repository.inspectContactDetailsById(contactId);
        if (contactDetails == null)
            throw new NotFoundException();
        final List<InteractionSummary> InteractionSummaries = repository.inspectContactInteractions(contactId);
        return new ContactDetailsWithInteractions(contactDetails, InteractionSummaries);
    }
}
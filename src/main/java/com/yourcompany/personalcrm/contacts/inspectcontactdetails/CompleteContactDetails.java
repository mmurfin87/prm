package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import lombok.AllArgsConstructor;
import java.util.List;

import com.yourcompany.personalcrm.relationships.inspectcontactrelationships.RelationshipSummary;

@AllArgsConstructor
public class CompleteContactDetails
{
    public final ContactDetails contactDetails;
    public final List<InteractionSummary> interactions;
    public final List<RelationshipSummary> relationships;
}
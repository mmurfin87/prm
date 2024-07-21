package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class ContactDetailsWithInteractions
{
    public final ContactDetails contactDetails;
    public final List<InteractionSummary> interactions;
}
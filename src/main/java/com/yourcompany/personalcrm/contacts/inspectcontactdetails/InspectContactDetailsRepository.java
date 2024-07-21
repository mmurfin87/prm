package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;

public interface InspectContactDetailsRepository
{
    @SqlQuery("SELECT * FROM contacts WHERE id = :id")
    @RegisterConstructorMapper(ContactDetails.class)
    ContactDetails inspectContactDetailsById(@Bind("id") long id);

    @SqlQuery("SELECT id, started, type, summary FROM interactions WHERE contact_id = :contactId ORDER BY started DESC")
    @RegisterConstructorMapper(InteractionSummary.class)
    List<InteractionSummary> inspectContactInteractions(@Bind long contactId);
}
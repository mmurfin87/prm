package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;

public interface InspectContactDetailsRepository
{
    @SqlQuery("SELECT id, first_name, last_name, birthdate, DATEDIFF(YEAR, birthdate, CURRENT_DATE()) as age, gender, email, phone, company FROM contacts WHERE id = :id AND owner_id = :ownerId")
    @RegisterConstructorMapper(ContactDetails.class)
    ContactDetails inspectContactDetailsById(@Bind String ownerId, @Bind String id);

    @SqlQuery("SELECT id, started, type, summary FROM interactions WHERE contact_id = :contactId AND owner_id = :ownerId ORDER BY started DESC")
    @RegisterConstructorMapper(InteractionSummary.class)
    List<InteractionSummary> inspectContactInteractions(@Bind String ownerId, @Bind String contactId);
}
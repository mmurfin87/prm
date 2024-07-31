package com.yourcompany.personalcrm.contacts.listcontacts;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface ListContactsRepository
{
    @SqlQuery("SELECT id, first_name, last_name, birthdate, DATEDIFF(YEAR, birthdate, CURRENT_DATE()) as age, gender FROM contacts WHERE owner_id = :ownerId")
    @RegisterConstructorMapper(value = ContactSummary.class)
    List<ContactSummary> listContacts(@Bind String ownerId);
}
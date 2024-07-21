package com.yourcompany.personalcrm.contacts.deletecontact;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteContactRepository
{
    @SqlUpdate("DELETE FROM contacts WHERE id = :id")
    boolean deleteContact(@Bind("id") String contactId);
}
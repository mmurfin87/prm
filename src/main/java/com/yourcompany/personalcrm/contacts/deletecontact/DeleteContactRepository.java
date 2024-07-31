package com.yourcompany.personalcrm.contacts.deletecontact;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteContactRepository
{
    @SqlUpdate("DELETE FROM contacts WHERE id = :contactId AND owner_id = :ownerId")
    boolean deleteContact(@Bind String ownerId, @Bind String contactId);
}
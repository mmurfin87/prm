package com.yourcompany.personalcrm.contacts.updatecontact;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UpdateContactRepository
{
    @SqlUpdate("""
        UPDATE contacts
        SET 
            first_name = :firstName,
            last_name = :lastName,
            email = :email,
            phone = :phone,
            company = :company
        WHERE 
            id = :id
        """)
    int updateContact(@Bind String id, @BindFields UpdateContactRequest request);
}
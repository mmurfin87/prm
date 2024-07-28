package com.yourcompany.personalcrm.contacts.updatecontact;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.yourcompany.personalcrm.contacts.inspectcontactdetails.ContactDetails;

public interface UpdateContactRepository
{
    @SqlQuery("SELECT id, first_name, last_name, birthdate, DATEDIFF(YEAR, birthdate, CURRENT_DATE()) as age, gender, email, phone, company FROM contacts WHERE id = :id")
    @RegisterConstructorMapper(ContactDetails.class)
    ContactDetails inspectContactDetailsById(@Bind String id);
    
    @SqlUpdate("""
        UPDATE contacts
        SET 
            first_name = :firstName,
            last_name = :lastName,
            birthdate = :birthdate,
            gender = :gender,
            email = :email,
            phone = :phone,
            company = :company
        WHERE 
            id = :id
        """)
    int updateContact(@Bind String id, @BindFields UpdateContactRequest request);
}
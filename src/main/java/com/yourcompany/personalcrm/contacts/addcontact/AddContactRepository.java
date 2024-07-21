package com.yourcompany.personalcrm.contacts.addcontact;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface AddContactRepository
{
    @SqlUpdate("INSERT INTO contacts (first_name, last_name, email, phone, company) VALUES (:firstName, :lastName, :email, :phone, :company)")
    @GetGeneratedKeys("id")
    long insert(@Bind("firstName") String firstName, 
                @Bind("lastName") String lastName, 
                @Bind("email") String email, 
                @Bind("phone") String phone, 
                @Bind("company") String company);
}
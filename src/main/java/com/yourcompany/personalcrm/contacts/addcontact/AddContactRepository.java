package com.yourcompany.personalcrm.contacts.addcontact;

import java.time.LocalDate;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface AddContactRepository
{
    @SqlUpdate("INSERT INTO contacts (owner_id, first_name, last_name, birthdate, gender, email, phone, company) VALUES (:ownerId, :firstName, :lastName, :birthdate, :gender, :email, :phone, :company)")
    @GetGeneratedKeys("id")
    long insert(
        @Bind String ownerId,
        @Bind String firstName,
        @Bind String lastName,
        @Bind LocalDate birthdate,
        @Bind String gender,
        @Bind String email, 
        @Bind String phone, 
        @Bind String company);
}
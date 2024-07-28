package com.yourcompany.personalcrm.contacts.addcontact;

import java.time.LocalDate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddContactRequest {
    public final String firstName;
    public final String lastName;
    public final LocalDate birthdate;
    public final String gender;
    public final String email;
    public final String phone;
    public final String company;
}
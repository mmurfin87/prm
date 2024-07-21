package com.yourcompany.personalcrm.contacts.addcontact;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddContactRequest {
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String phone;
    public final String company;
}
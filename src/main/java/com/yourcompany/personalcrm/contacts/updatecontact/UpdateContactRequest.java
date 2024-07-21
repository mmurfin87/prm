package com.yourcompany.personalcrm.contacts.updatecontact;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateContactRequest
{
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String phone;
    public final String company;
}
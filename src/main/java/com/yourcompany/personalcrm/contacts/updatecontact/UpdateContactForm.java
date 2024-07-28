package com.yourcompany.personalcrm.contacts.updatecontact;

import java.util.List;

import com.yourcompany.personalcrm.util.FormOption;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateContactForm
{
    public final String id;
    public final String firstName;
    public final String lastName;
    public final String birthdate;
    public final List<FormOption> genderOptions;
    public final String email;
    public final String phone;
    public final String company;
}

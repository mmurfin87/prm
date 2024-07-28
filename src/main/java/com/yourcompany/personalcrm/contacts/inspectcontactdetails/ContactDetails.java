package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import java.time.LocalDate;

import com.yourcompany.personalcrm.util.Dates;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContactDetails
{
    public final String id;
    public final String firstName;
    public final String lastName;
    public final LocalDate birthdate;
    public final int age;
    public final String gender;
    public final String email;
    public final String phone;
    public final String company;

    public String birthdateFormatted()
    {
        return birthdate == null ? null : birthdate.format(Dates.MONTH_DAY_COMMA_YEAR);
    }
}
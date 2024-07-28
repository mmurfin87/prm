package com.yourcompany.personalcrm.contacts.listcontacts;

import java.time.LocalDate;
import com.yourcompany.personalcrm.util.Dates;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ContactSummary
{
    @NonNull
    public final String id;
    @NonNull
    public final String firstName;
    @NonNull
    public final String lastName;
    public final LocalDate birthdate;
    public final int age;
    public final String gender;

    public String birthdateFormatted()
    {
        return birthdate == null ? null : birthdate.format(Dates.MONTH_DAY_COMMA_YEAR);
    }
}
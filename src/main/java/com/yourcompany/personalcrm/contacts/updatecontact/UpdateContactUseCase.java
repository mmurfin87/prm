package com.yourcompany.personalcrm.contacts.updatecontact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.contacts.inspectcontactdetails.ContactDetails;
import com.yourcompany.personalcrm.login.User;
import com.yourcompany.personalcrm.util.Dates;
import com.yourcompany.personalcrm.util.FormOption;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class UpdateContactUseCase
{
    public UpdateContactForm prepareForm(@NonNull final String id)
    {
        final ContactDetails details = repository.inspectContactDetailsById(User.identify(), id);
        final List<FormOption> genderOptions = new ArrayList<>(3);
        genderOptions.add(new FormOption("Male", "Male", "Male".equalsIgnoreCase(details.gender)));
        genderOptions.add(new FormOption("Female", "Female", "Female".equalsIgnoreCase(details.gender)));
        if (details.gender != null && !details.gender.isBlank() && genderOptions.stream().noneMatch(fo -> fo.selected))
            genderOptions.add(new FormOption(details.gender, details.gender, true));
        final String birthdate = details.birthdate == null ? null : details.birthdate.format(Dates.YYYY_MM_DD);
        return new UpdateContactForm(details.id, details.firstName, details.lastName, birthdate, genderOptions, details.email, details.phone, details.company);
    }

    public void execute(@NonNull final String id, @NonNull final UpdateContactRequest request)
    {
        int updatedRows = repository.updateContact(User.identify(), id, request);
        if (updatedRows == 0)
            throw new IllegalArgumentException("Contact not found with id: " + id);
    }

    @NonNull
    private final UpdateContactRepository repository;
}
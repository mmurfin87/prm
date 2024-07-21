package com.yourcompany.personalcrm.contacts.inspectcontactdetails;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class InteractionSummary
{
    @NonNull
    public final String id;
    @NonNull
    public final LocalDateTime started;
    @NonNull
    public final String type;
    public final String summary;

    public String formatedStarted()
    {
        return started.format(startedFormat);
    }

    private final static DateTimeFormatter startedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
}
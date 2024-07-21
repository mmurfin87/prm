package com.yourcompany.personalcrm.interactions.addinteraction;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddInteractionRequest
{
    @NonNull
    public final String contactId;
    @NonNull
    public final String type;
    @NonNull
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public final LocalDateTime started;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public final LocalDateTime ended;
    public final String summary;
    public final String location;
}
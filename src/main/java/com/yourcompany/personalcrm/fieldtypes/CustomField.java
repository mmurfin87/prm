package com.yourcompany.personalcrm.fieldtypes;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CustomField
{
    @NonNull
    public final String id;
    @NonNull
    public final String name;
    @NonNull
    public final String fieldTypeId;
    public final boolean isRequired;
    public final String defaultValue;
}
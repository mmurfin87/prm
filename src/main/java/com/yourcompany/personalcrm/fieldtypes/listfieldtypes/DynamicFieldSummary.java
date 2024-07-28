package com.yourcompany.personalcrm.fieldtypes.listfieldtypes;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DynamicFieldSummary
{
    @NonNull
    public final String id;
    @NonNull
    public final String name;
    @NonNull
    public final String fieldTypeId;
    @NonNull
    public final String fieldTypeName;
    public final boolean isRequired;
    public final String defaultValue;
}
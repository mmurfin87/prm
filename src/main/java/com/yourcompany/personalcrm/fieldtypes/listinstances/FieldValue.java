package com.yourcompany.personalcrm.fieldtypes.listinstances;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class FieldValue
{
    @NonNull
    public final String id;
    @NonNull
    public final String fieldName;
    @NonNull
    public final String fieldTypeId;
    @NonNull
    public final String fieldTypeName;
    public final String value;
}

package com.yourcompany.personalcrm.fieldtypes.listinstances;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Instance
{
    @NonNull
    public final String id;
    @NonNull
    public final String fieldTypeId;
    @NonNull
    public final String fieldTypeName;
    @NonNull
    public final List<FieldValue> fields;
}

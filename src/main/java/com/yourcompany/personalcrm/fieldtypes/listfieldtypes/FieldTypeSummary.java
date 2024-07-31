package com.yourcompany.personalcrm.fieldtypes.listfieldtypes;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class FieldTypeSummary
{
    @NonNull
    public final String id;
    @NonNull
    public final String name;
    @NonNull
    public final String pkg;
    public final String description;
    public final String baseTypeId;
    public final String baseTypeName;
    public final boolean isInstantiable;
    public final List<DynamicFieldSummary> fields;

    public boolean isCustom()
    {
        return !"core".equalsIgnoreCase(pkg);
    }
}
package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

import com.yourcompany.personalcrm.fieldtypes.CustomField;

@AllArgsConstructor
public class AddCustomFieldTypeResponse
{
    @NonNull
    public final String id;
    @NonNull
    public final String name;
    @NonNull
    public final String pkg;
    public final String description;
    public final String baseTypeId;
    public final List<CustomField> fields;
}
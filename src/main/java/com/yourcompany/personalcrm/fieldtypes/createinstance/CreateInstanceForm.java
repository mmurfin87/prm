package com.yourcompany.personalcrm.fieldtypes.createinstance;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yourcompany.personalcrm.util.FormOption;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CreateInstanceForm
{
    public final String fieldTypeId;
    public final String fieldTypeName;
    @NonNull
    public final List<FormOption> fieldTypeOptions;
    @NonNull
    @JsonIgnore
    public final String javascriptMapOfFieldTypes;
}
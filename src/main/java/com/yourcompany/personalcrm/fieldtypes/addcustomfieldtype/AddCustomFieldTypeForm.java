package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yourcompany.personalcrm.util.FormOption;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddCustomFieldTypeForm 
{
    @NonNull
    public final List<FormOption> baseTypeOptions;
    @NonNull
    @JsonIgnore
    public final String javascriptArrayBaseTypeOptions;
}

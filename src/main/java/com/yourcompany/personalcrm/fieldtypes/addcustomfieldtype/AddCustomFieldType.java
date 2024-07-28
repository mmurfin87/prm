package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
public class AddCustomFieldType
{
    public String name;
    public String fieldTypeId;
    public boolean isRequired;
    public String defaultValue;
}
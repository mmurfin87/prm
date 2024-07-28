package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
public class AddCustomFieldTypeRequest
{
    public String name;
    public String baseTypeId;
    public String description;
    public List<AddCustomFieldType> fields; // Only applicable if baseType is "Object"
}
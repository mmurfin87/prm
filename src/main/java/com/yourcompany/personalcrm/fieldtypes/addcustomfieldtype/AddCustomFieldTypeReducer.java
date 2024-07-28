package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import com.yourcompany.personalcrm.fieldtypes.CustomField;

import java.util.ArrayList;
import java.util.Map;

public class AddCustomFieldTypeReducer implements LinkedHashMapRowReducer<String, AddCustomFieldTypeResponse>
{
    @Override
    public void accumulate(Map<String, AddCustomFieldTypeResponse> container, RowView rowView) {
        String id = rowView.getColumn("id", String.class);
        AddCustomFieldTypeResponse response = container.computeIfAbsent(id, k -> new AddCustomFieldTypeResponse(
            id,
            rowView.getColumn("name", String.class),
            rowView.getColumn("package", String.class),
            rowView.getColumn("description", String.class),
            rowView.getColumn("base_type_id", String.class),
            new ArrayList<>()
        ));

        String fieldId = rowView.getColumn("field_id", String.class);
        if (fieldId != null) {
            response.fields.add(new CustomField(
                fieldId,
                rowView.getColumn("field_name", String.class),
                rowView.getColumn("field_type_id", String.class),
                rowView.getColumn("is_required", Boolean.class),
                rowView.getColumn("default_value", String.class)
            ));
        }
    }
}
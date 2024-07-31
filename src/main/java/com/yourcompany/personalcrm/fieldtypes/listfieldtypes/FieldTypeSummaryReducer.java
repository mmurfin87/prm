package com.yourcompany.personalcrm.fieldtypes.listfieldtypes;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.ArrayList;
import java.util.Map;

public class FieldTypeSummaryReducer implements LinkedHashMapRowReducer<String, FieldTypeSummary>
{
    @Override
    public void accumulate(Map<String, FieldTypeSummary> container, RowView rv)
    {
        final String id = rv.getColumn("id", String.class);
        final FieldTypeSummary fieldType = container.computeIfAbsent(id, key -> new FieldTypeSummary(
                id,
                rv.getColumn("name", String.class),
                rv.getColumn("package", String.class),
                rv.getColumn("description", String.class),
                rv.getColumn("base_type_id", String.class),
                rv.getColumn("base_type_name", String.class),
                rv.getColumn("is_instantiable", Boolean.class),
                new ArrayList<>()
            ));
        
        String fieldId = rv.getColumn("field_id", String.class);
        if (fieldId != null) {
            DynamicFieldSummary dynamicField = new DynamicFieldSummary(
                fieldId,
                rv.getColumn("field_name", String.class),
                rv.getColumn("field_type_id", String.class),
                rv.getColumn("field_type_name", String.class),
                rv.getColumn("is_required", Boolean.class),
                rv.getColumn("default_value", String.class)
            );
            fieldType.fields.add(dynamicField);
        }
    }
}
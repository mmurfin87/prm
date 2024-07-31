package com.yourcompany.personalcrm.fieldtypes.listinstances;

import java.util.ArrayList;
import java.util.Map;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

public class InstanceRowReducer implements LinkedHashMapRowReducer<String, Instance>
{
    @Override
    public void accumulate(Map<String, Instance> container, RowView rv)
    {
        final String instanceId = rv.getColumn("e_id", String.class);
        final Instance instance = container.computeIfAbsent(instanceId, key -> new Instance(
            instanceId,
            rv.getColumn("e_field_type_id", String.class),
            rv.getColumn("e_field_type_name", String.class),
            new ArrayList<>()
        ));

        instance.fields.add(new FieldValue(
            rv.getColumn("id", String.class),
            rv.getColumn("field_name", String.class),
            rv.getColumn("field_type_id", String.class),
            rv.getColumn("field_type_name", String.class),
            rv.getColumn("value_literal", String.class)
        ));
    }   
}

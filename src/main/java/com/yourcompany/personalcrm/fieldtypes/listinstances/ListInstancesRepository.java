package com.yourcompany.personalcrm.fieldtypes.listinstances;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

public interface ListInstancesRepository
{
    @SqlQuery("""
        SELECT
            de.id as e_id,
            de.field_type_id as e_field_type_id,
            ft.name as e_field_type_name,
            dfv.id,
            df.name as field_name,
            df.id as field_type_id,
            fft.name as field_type_name,
            dfv.value_literal
        FROM dynamic_entities de
        INNER JOIN field_types ft ON
            de.field_type_id = ft.id
        INNER JOIN dynamic_fields df ON
            ft.id = df.parent_field_type_id
        INNER JOIN field_types fft ON
            df.field_type_id = fft.id
        LEFT JOIN dynamic_field_values dfv ON
            de.id = dfv.dynamic_entity_id
            AND
            df.id = dfv.dynamic_field_id
        WHERE
            de.field_type_id = :fieldTypeId
        """)
    @UseRowReducer(InstanceRowReducer.class)
    List<Instance> listInstances(@Bind String fieldTypeId);
}

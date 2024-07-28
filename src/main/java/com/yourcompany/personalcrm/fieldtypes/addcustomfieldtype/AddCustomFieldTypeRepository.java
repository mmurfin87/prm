package com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype;

import java.util.Map;

import org.jdbi.v3.sqlobject.config.KeyColumn;
import org.jdbi.v3.sqlobject.config.ValueColumn;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

public interface AddCustomFieldTypeRepository
{
    @KeyColumn("id")
    @ValueColumn("name")
    @SqlQuery("SELECT id, name FROM field_types WHERE package = :pkg")
    Map<String, String> getFieldTypesByPackage(@Bind String pkg);

    @SqlQuery("SELECT id FROM field_types WHERE name = :name AND package = 'core'")
    String getFieldTypeIdByName(@Bind String name);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO field_types (name, package, description, base_type_id) VALUES (:name, :pkg, :description, :baseTypeId)")
    String addCustomFieldType(@Bind String name, @Bind String pkg, @Bind String description, @Bind String baseTypeId);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO dynamic_fields (parent_field_type_id, name, field_type_id, is_required, default_value) VALUES (:parentFieldTypeId, :name, :fieldTypeId, :isRequired, :defaultValue)")
    String addDynamicField(@Bind String parentFieldTypeId, @Bind String name, @Bind String fieldTypeId, @Bind boolean isRequired, @Bind String defaultValue);
    
    @SqlQuery("""
        SELECT 
            ft.id,
            ft.name,
            ft.package,
            ft.description,
            ft.base_type_id,
            df.id as field_id,
            df.name as field_name,
            df.field_type_id,
            df.is_required,
            df.default_value
        FROM field_types ft
        LEFT JOIN dynamic_fields df 
            ON ft.id = df.field_type_id
        WHERE 
            ft.id = :id
        """)
    @UseRowReducer(AddCustomFieldTypeReducer.class)
    AddCustomFieldTypeResponse getCustomFieldTypeById(@Bind String id);
}
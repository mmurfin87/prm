package com.yourcompany.personalcrm.fieldtypes.listfieldtypes;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

public interface ListFieldTypesRepository
{
    public static final String BASE_QUERY = """
        WITH RECURSIVE field_type_hierarchy (id, package, name, is_instantiable, description, base_type_id, base_type_name, level) AS
        (
            SELECT 
                c.id, 
                c.package, 
                c.name, 
                c.is_instantiable,
                c.description, 
                c.base_type_id,
                null,
                0
            FROM field_types c
            WHERE
                c.base_type_id IS NULL
            
            UNION ALL
            
            SELECT 
                ft.id, 
                ft.package,
                ft.name, 
                ft.is_instantiable,
                ft.description,
                ft.base_type_id,
                fth.name as base_type_name,
                fth.level + 1
            FROM field_types ft
            INNER JOIN field_type_hierarchy fth ON
                ft.base_type_id = fth.id
        )
        SELECT
            fth.id,
            fth.name,
            fth.package,
            fth.description,
            fth.is_instantiable,
            fth.base_type_id,
            fth.base_type_name,
            df.id as field_id,
            df.name as field_name,
            df.field_type_id,
            ft.name as field_type_name,
            df.is_required,
            df.default_value
        FROM field_type_hierarchy fth
        LEFT JOIN dynamic_fields df ON
            fth.id = df.parent_field_type_id
        LEFT JOIN field_types ft ON
            df.field_type_id = ft.id
        """;

    @SqlQuery(BASE_QUERY + "WHERE fth.id = :id")
    @UseRowReducer(FieldTypeSummaryReducer.class)
    FieldTypeSummary inspectFieldType(@Bind String id);
    
    @SqlQuery(BASE_QUERY + "ORDER BY fth.level, fth.package, fth.name")
    @UseRowReducer(FieldTypeSummaryReducer.class)
    List<FieldTypeSummary> listFieldTypes();

    @SqlQuery(BASE_QUERY + """
            WHERE
                fth.package = :pkg
            ORDER BY fth.level, fth.name
            """)
    @UseRowReducer(FieldTypeSummaryReducer.class)
    List<FieldTypeSummary> listFieldTypesByPackage(@Bind("package") String pkg);
}
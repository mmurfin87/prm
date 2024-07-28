package com.yourcompany.personalcrm.fieldtypes.deletecustomfieldtype;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface DeleteCustomFieldTypeRepository {
    @SqlQuery("""
        WITH RECURSIVE field_type_tree (id, base_type_id) AS 
        (
            SELECT
                id,
                base_type_id
            FROM field_types
            WHERE
                id = :id
            
            UNION ALL
            
            SELECT
                ft.id,
                ft.base_type_id
            FROM field_types ft
            INNER JOIN field_type_tree ftt ON
                ft.base_type_id = ftt.id
        )
        SELECT id FROM field_type_tree
        ORDER BY base_type_id NULLS LAST
        """)
    List<String> getFieldTypeHierarchy(@Bind("id") String id);

    @SqlUpdate("DELETE FROM dynamic_fields WHERE parent_field_type_id = :id")
    int deleteDynamicFields(@Bind("id") String id);

    @SqlUpdate("DELETE FROM field_types WHERE id = :id AND package = 'user'")
    int deleteCustomFieldType(@Bind("id") String id);

    @SqlQuery("SELECT COUNT(*) FROM field_types WHERE base_type_id = :id")
    int countDependentTypes(@Bind("id") String id);

    @SqlQuery("SELECT COUNT(*) FROM dynamic_fields WHERE field_type_id = :id")
    int countDependentFields(@Bind("id") String id);
}
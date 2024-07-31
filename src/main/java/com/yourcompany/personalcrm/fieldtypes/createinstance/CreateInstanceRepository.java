package com.yourcompany.personalcrm.fieldtypes.createinstance;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CreateInstanceRepository {
    @SqlUpdate("INSERT INTO dynamic_entities (field_type_id) VALUES (:fieldTypeId)")
    @GetGeneratedKeys
    String createDynamicEntity(@Bind("fieldTypeId") String fieldTypeId);

    @SqlUpdate("INSERT INTO dynamic_field_values (dynamic_entity_id, dynamic_field_id, value_literal) VALUES (:entityId, :fieldId, :value)")
    void addDynamicFieldValue(@Bind("entityId") String entityId, @Bind("fieldId") String fieldId, @Bind("value") String value);
}
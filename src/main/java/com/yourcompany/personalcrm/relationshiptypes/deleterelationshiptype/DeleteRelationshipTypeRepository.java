package com.yourcompany.personalcrm.relationshiptypes.deleterelationshiptype;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteRelationshipTypeRepository
{
    @SqlUpdate("UPDATE relationship_types SET counter_relationship_type_id = NULL WHERE counter_relationship_type_id = :id")
    int clearCounterpartReferences(@Bind String id);

    @SqlUpdate("DELETE FROM relationship_types WHERE id = :id")
    int delete(@Bind String id);
}
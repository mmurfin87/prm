package com.yourcompany.personalcrm.relationshiptypes.deleterelationshiptype;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteRelationshipTypeRepository
{
    @SqlUpdate("UPDATE relationship_types SET counter_relationship_type_id = NULL WHERE owner_id = :ownerId AND counter_relationship_type_id = :id")
    int clearCounterpartReferences(@Bind String ownerId, @Bind String id);

    @SqlUpdate("DELETE FROM relationship_types WHERE owner_id = :ownerId AND id = :id")
    int delete(@Bind String ownerId, @Bind String id);
}
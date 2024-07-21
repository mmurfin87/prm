package com.yourcompany.personalcrm.relationshiptypes.deleterelationshiptype;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteRelationshipTypeRepository
{
    @SqlUpdate("DELETE FROM relationship_types WHERE id = :id")
    int delete(@Bind("id") String id);
}
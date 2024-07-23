package com.yourcompany.personalcrm.relationships.deleterelationship;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteRelationshipRepository
{
    @SqlUpdate("DELETE FROM relationships WHERE id = :id")
    int deleteId(@Bind String id);
}

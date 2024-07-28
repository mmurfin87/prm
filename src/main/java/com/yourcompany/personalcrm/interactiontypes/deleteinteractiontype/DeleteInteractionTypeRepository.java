package com.yourcompany.personalcrm.interactiontypes.deleteinteractiontype;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteInteractionTypeRepository
{
    @SqlUpdate("DELETE FROM interaction_types WHERE id = :id")
    int delete(String id);
}
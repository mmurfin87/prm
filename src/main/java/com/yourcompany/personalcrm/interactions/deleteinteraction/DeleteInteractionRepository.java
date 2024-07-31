package com.yourcompany.personalcrm.interactions.deleteinteraction;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteInteractionRepository 
{
    @SqlUpdate("DELETE FROM interactions WHERE owner_id = :ownerId AND id = :id")
    int deleteById(@Bind String ownerId, @Bind String id);
}

package com.yourcompany.personalcrm.interactions.deleteinteraction;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeleteInteractionRepository 
{
    @SqlUpdate("DELETE FROM interactions WHERE id = :id")
    int deleteById(@Bind String id);
}

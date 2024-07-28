package com.yourcompany.personalcrm.interactiontypes.addinteractiontype;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface AddInteractionTypeRepository
{
    @SqlQuery("SELECT COUNT(1) FROM interaction_types WHERE name = :name")
    public int exists(@Bind String name);

    @SqlUpdate("INSERT INTO interaction_types (name) VALUES (:name)")
    @GetGeneratedKeys
    String insert(@Bind String name);
}
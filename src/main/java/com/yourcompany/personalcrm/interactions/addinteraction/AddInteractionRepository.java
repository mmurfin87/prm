package com.yourcompany.personalcrm.interactions.addinteraction;

import java.util.Map;

import org.jdbi.v3.sqlobject.config.KeyColumn;
import org.jdbi.v3.sqlobject.config.ValueColumn;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface AddInteractionRepository
{
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO interactions (owner_id, contact_id, type, started, ended, summary, location) VALUES (:ownerId, :contactId, :type, :started, :ended, :summary, :location)")
    String insert(@Bind String ownerId, @BindFields AddInteractionRequest request);

    @KeyColumn("id")
    @ValueColumn("full_name")
    @SqlQuery("SELECT id, CONCAT(first_name, ' ', last_name) AS full_name FROM contacts")
    Map<String, String> getContactIdNameMap();

    @KeyColumn("id")
    @ValueColumn("name")
    @SqlQuery("SELECT id, name FROM interaction_types")
    Map<String, String> getInteractionTypes();
}
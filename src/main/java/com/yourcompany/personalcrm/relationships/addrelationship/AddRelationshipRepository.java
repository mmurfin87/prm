package com.yourcompany.personalcrm.relationships.addrelationship;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Map;

import org.jdbi.v3.sqlobject.config.KeyColumn;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.config.ValueColumn;
import org.jdbi.v3.sqlobject.customizer.Bind;

public interface AddRelationshipRepository
{
    @SqlQuery("SELECT id, first_name || ' ' || last_name as name FROM contacts")
    @KeyColumn("id")
    @ValueColumn("name")
    Map<String, String> listContactIdsAndNames();

    @SqlQuery("""
        SELECT
            rt.id,
            rt.name,
            rt.counter_relationship_type_id as counterpartId,
            rti.name as counterpartName
        FROM relationship_types rt
        LEFT JOIN relationship_types rti ON
            rt.counter_relationship_type_id = rti.id
        WHERE
            rt.id = :id
        """)
    @RegisterConstructorMapper(RelationshipType.class)
    RelationshipType inspectRelationshipType(@Bind String id);

    @SqlQuery("""
        SELECT
            rt.id,
            rt.name,
            rt.counter_relationship_type_id as counterpartId,
            rti.name as counterpartName
        FROM relationship_types rt
        LEFT JOIN relationship_types rti ON
            rt.counter_relationship_type_id = rti.id
        WHERE
            NOT EXISTS (SELECT 1 FROM relationships WHERE origin_contact_id = :originContactId AND target_contact_id = :targetContactId AND relationship_type_id = rt.id)
        """)
    List<RelationshipType> availableRelationshipTypesForContactPair(@Bind String originContactId, @Bind String targetContactId);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO relationships (owner_id, origin_contact_id, target_contact_id, relationship_type_id, notes) VALUES (:ownerId, :originContactId, :TargetContactId, :relationshipTypeId, :notes)")
    String addRelationship(@Bind String ownerId, @Bind String originContactId, @Bind String TargetContactId, @Bind String relationshipTypeId, @Bind String notes);
}
package com.yourcompany.personalcrm.relationships.listrelationships;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface ListRelationshipsRepository
{
    @SqlQuery("""
        SELECT
            r.id,
            r.origin_contact_id,
            c1.first_name || ' ' || c1.last_name as originContactName,
            r.target_contact_id,
            c2.first_name || ' ' || c2.last_name as targetContactName,
            r.relationship_type_id,
            rt.name as relationshipTypeName
        FROM relationships r
        INNER JOIN contacts c1 ON
            r.origin_contact_id = c1.id
        INNER JOIN contacts c2 ON
            r.target_contact_id = c2.id
        INNER JOIN relationship_types rt ON
            r.relationship_type_id = rt.id
        """)
    @RegisterConstructorMapper(Relationship.class)
    List<Relationship> findAll();
}

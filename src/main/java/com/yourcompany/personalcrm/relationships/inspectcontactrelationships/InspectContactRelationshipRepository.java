package com.yourcompany.personalcrm.relationships.inspectcontactrelationships;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import java.util.List;

public interface InspectContactRelationshipRepository
{
    @SqlQuery("""
        SELECT 
            r.id,
            r.target_contact_id as contactId,
            c.first_name || ' ' || c.last_name as contactName,
            rt.id as relationshipTypeId,
            rt.name as relationshipTypeName
        FROM relationships r
        INNER JOIN contacts c ON
            r.target_contact_id = c.id
        INNER JOIN relationship_types rt ON
            r.relationship_type_id = rt.id
        WHERE
            r.origin_contact_id = :contactId
        """)
    @RegisterConstructorMapper(RelationshipSummary.class)
    List<RelationshipSummary> getRelationshipsForContact(@Bind String contactId);
}
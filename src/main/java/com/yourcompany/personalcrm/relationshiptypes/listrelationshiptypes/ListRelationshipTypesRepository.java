package com.yourcompany.personalcrm.relationshiptypes.listrelationshiptypes;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;

import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import java.util.List;

public interface ListRelationshipTypesRepository
{
    @SqlQuery("""
        SELECT
            rt.id,
            rt.name,
            rt.counter_relationship_type_id as counterpartId,
            rti.name as counterpartName
        FROM relationship_types rt
        LEFT JOIN relationship_types rti ON
            rt.counter_relationship_type_id = rti.id
        """)
    @RegisterConstructorMapper(RelationshipType.class)
    List<RelationshipType> findAll();
}
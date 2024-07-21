package com.yourcompany.personalcrm.relationshiptypes.listrelationshiptypes;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;

import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import java.util.List;

public interface ListRelationshipTypesRepository
{
    @SqlQuery("""
        SELECT
            id,
            name,
            origin_male_name originMaleName,
            origin_female_name originFemaleName,
            target_male_name targetMaleName,
            target_female_name targetFemaleName
        FROM relationship_types""")
    @RegisterConstructorMapper(RelationshipType.class)
    List<RelationshipType> findAll();
}
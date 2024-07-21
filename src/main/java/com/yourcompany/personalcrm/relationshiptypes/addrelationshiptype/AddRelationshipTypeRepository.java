package com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype;

import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;

public interface AddRelationshipTypeRepository
{
    @SqlUpdate("""
        INSERT INTO relationship_types (name, origin_male_name, origin_female_name, target_male_name, target_female_name) 
        VALUES (:name, :originMaleName, :originFemaleName, :targetMaleName, :targetFemaleName)""")
    @GetGeneratedKeys("id")
    String add(@BindFields AddRelationshipTypeRequest request);
}
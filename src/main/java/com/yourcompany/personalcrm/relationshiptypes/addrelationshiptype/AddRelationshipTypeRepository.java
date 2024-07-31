package com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.yourcompany.personalcrm.util.Tuple2;

import lombok.NonNull;

import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;

public interface AddRelationshipTypeRepository
{
    @SqlUpdate("INSERT INTO relationship_types (owner_id, name, counter_relationship_type_id) VALUES (:ownerId, :name, :counterRelationshipTypeId)")
    @GetGeneratedKeys("id")
    String add(@Bind String ownerId, @Bind String name, @Bind String counterRelationshipTypeId);

    @SqlUpdate("UPDATE relationship_types SET counter_relationship_type_id = :counterRelationshipTypeId WHERE ower_id = :ownerId AND id = :id")
    int setCounterRelationshipType(@Bind String ownerId, @Bind String id, @Bind String counterRelationshipTypeId);

    @Transaction
    default String addSelfCounterpart(@NonNull final String ownerId, @NonNull final String name)
    {
        final String id = add(ownerId, name, null);
        setCounterRelationshipType(ownerId, id, id);
        return id;
    }

    @Transaction
    default Tuple2<String, String> addPair(@NonNull final String ownerId, @NonNull final String name1, @NonNull final String name2)
    {
        final String id1 = add(ownerId, name1, null);
        final String id2 = add(ownerId, name2, id1);
        setCounterRelationshipType(ownerId, id1, id2);
        return new Tuple2<String,String>(id1, id2);
    }
}
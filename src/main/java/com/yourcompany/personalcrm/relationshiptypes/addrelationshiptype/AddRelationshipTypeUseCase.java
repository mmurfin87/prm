package com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;
import com.yourcompany.personalcrm.util.Strings;
import com.yourcompany.personalcrm.util.Tuple2;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class AddRelationshipTypeUseCase
{
    @NonNull
    private final AddRelationshipTypeRepository repository;

    public RelationshipType execute(@NonNull final String name)
    {
        final String id = repository.add(name, null);
        return new RelationshipType(id, name, null, null);
    }

    public List<RelationshipType> executeCounterPair(@NonNull final String name1, @NonNull final String name2)
    {
        if (Strings.equalsIgnoreCase(name1, name2))
        {
            final String id = repository.addSelfCounterpart(name1);
            return List.of(new RelationshipType(id, name1, id, name1));
        }
        else
        {
            final Tuple2<String, String> ids = repository.addPair(name1, name2);
            return List.of(new RelationshipType(ids.a, name1, ids.b, name2), new RelationshipType(ids.b, name2, ids.a, name1));
        }
    }
}
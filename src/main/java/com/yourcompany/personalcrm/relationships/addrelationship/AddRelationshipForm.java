package com.yourcompany.personalcrm.relationships.addrelationship;

import java.util.List;

import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddRelationshipForm
{
    public final List<AddRelationshipContactOption> originContactOptions;
    public final List<AddRelationshipContactOption> targetContactOptions;
    public final List<RelationshipType> relationshipTypeOptions;
}

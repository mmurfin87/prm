package com.yourcompany.personalcrm.relationships.addrelationship;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddRelationshipRequest
{
    @NonNull
    public final String originContactId;
    
    @NonNull
    public final String targetContactId;
    
    @NonNull
    public final String relationshipTypeId;
    
    public final String notes;
}
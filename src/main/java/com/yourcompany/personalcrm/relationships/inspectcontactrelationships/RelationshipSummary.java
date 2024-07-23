package com.yourcompany.personalcrm.relationships.inspectcontactrelationships;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class RelationshipSummary
{
    @NonNull
    public final String id;
    
    @NonNull
    public final String contactId;
    
    @NonNull
    public final String contactName;
    
    @NonNull
    public final String relationshipTypeId;
    
    @NonNull
    public final String relationshipTypeName;
}
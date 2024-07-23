package com.yourcompany.personalcrm.relationships.listrelationships;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Relationship
{
    @NonNull
    public final String id;
    @NonNull
    public final String originContactId;
    @NonNull
    public final String originContactName;
    @NonNull
    public final String targetContactId;
    @NonNull
    public final String targetContactName;
    @NonNull
    public final String relationshipTypeId;
    @NonNull
    public final String relationshipTypeName;
}
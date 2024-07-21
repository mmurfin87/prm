package com.yourcompany.personalcrm.relationshiptypes;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class RelationshipType
{
    @NonNull
    public final String id;
    @NonNull
    public final String name;
    @NonNull
    public final String originMaleName;
    @NonNull
    public final String originFemaleName;
    @NonNull
    public final String targetMaleName;
    @NonNull
    public final String targetFemaleName;
}
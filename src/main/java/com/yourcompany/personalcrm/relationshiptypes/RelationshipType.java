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
    public final String counterpartId;
    public final String counterpartName;
}
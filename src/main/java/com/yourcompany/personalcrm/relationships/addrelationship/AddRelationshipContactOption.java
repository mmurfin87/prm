package com.yourcompany.personalcrm.relationships.addrelationship;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddRelationshipContactOption
{
    @NonNull
    public final String id;
    @NonNull
    public final String name;
    public final boolean selected;
}

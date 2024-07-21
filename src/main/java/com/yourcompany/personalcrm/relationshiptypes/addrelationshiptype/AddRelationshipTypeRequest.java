package com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddRelationshipTypeRequest
{
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
package com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddRelationshipTypeRequest
{
    @NonNull
    public final String name;
    public final String counterName;
}
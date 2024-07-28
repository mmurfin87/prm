package com.yourcompany.personalcrm.interactiontypes;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class InteractionType
{
    @NonNull
    public final String id;
    @NonNull
    public final String name;
}

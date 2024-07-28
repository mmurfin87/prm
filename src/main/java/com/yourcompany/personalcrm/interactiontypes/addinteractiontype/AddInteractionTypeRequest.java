package com.yourcompany.personalcrm.interactiontypes.addinteractiontype;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddInteractionTypeRequest
{
    @NonNull
    public final String name;
}

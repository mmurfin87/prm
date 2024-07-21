package com.yourcompany.personalcrm.interactions.addinteraction;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddInteractionForm
{
    @NonNull
    public final List<AddInteractionContactOption> contactOptions;
    public final String type;
    public final String started;
    public final String ended;
    public final String summary;
    public final String location;
}

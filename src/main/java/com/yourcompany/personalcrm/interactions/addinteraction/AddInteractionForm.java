package com.yourcompany.personalcrm.interactions.addinteraction;

import java.util.List;

import com.yourcompany.personalcrm.util.FormOption;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddInteractionForm
{
    @NonNull
    public final List<FormOption> contactOptions;
    @NonNull
    public final List<FormOption> typeOptions;
    public final String type;
    public final String started;
    public final String ended;
    public final String summary;
    public final String location;
}

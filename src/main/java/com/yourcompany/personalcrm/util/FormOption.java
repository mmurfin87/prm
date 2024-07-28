package com.yourcompany.personalcrm.util;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class FormOption
{
    @NonNull
    public final String value;
    @NonNull
    public final String text;
    public boolean selected;
}

package com.yourcompany.personalcrm.fieldtypes.listinstances;

import java.util.List;

import com.yourcompany.personalcrm.fieldtypes.listfieldtypes.FieldTypeSummary;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class InstanceList
{
    @NonNull
    public final FieldTypeSummary fieldTypeSummary;
    @NonNull
    public final List<Instance> instances;
}

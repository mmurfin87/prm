package com.yourcompany.personalcrm.fieldtypes.listinstances;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.NotFoundException;
import com.yourcompany.personalcrm.fieldtypes.listfieldtypes.FieldTypeSummary;
import com.yourcompany.personalcrm.fieldtypes.listfieldtypes.ListFieldTypesRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class ListInstancesUseCase
{
    public InstanceList listInstances(@NonNull final String fieldTypeId)
    {
        final FieldTypeSummary fieldTypeSummary = ftRepo.inspectFieldType(fieldTypeId);
        if (fieldTypeSummary == null)
            throw new NotFoundException(fieldTypeId);
        final List<Instance> instances = iRepo.listInstances(fieldTypeId);
        orderInstanceFields(fieldTypeSummary.fields.stream().map(dfs -> dfs.name).toList(), instances);
        return new InstanceList(fieldTypeSummary, instances);
    }

    private static void orderInstanceFields(List<String> order, List<Instance> instances)
    {
        instances.forEach(instance ->
        {
            for (int i = 0; i < order.size(); i++)
            {
                for (int f = i; f < instances.size(); f++)
                {
                    final FieldValue field = instance.fields.get(f);
                    if (order.get(i).equals(field.fieldName))
                        continue;
                    
                    // Find the index p of the field we are looking for, the one corresponding to the current order value
                    int p = f + 1;
                    for (; p < instance.fields.size() && !order.get(i).equals(instance.fields.get(p).fieldName); p++);;
                    
                    // Swap f and p in instance.fields
                    if (p < instance.fields.size())
                    {
                        final FieldValue tmp = field;
                        instance.fields.set(f, instance.fields.get(p));
                        instance.fields.set(p, tmp);
                    }
                }
            }
        });
    }

    @NonNull
    private final ListInstancesRepository iRepo;
    @NonNull
    private final ListFieldTypesRepository ftRepo;
}

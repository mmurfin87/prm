package com.yourcompany.personalcrm.fieldtypes.listinstances;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@RestController
@AllArgsConstructor
@RequestMapping("field-types")
public class ListInstancesController
{
    @GetMapping("{id}/list")
    public InstanceList listInstances(@NonNull @PathVariable final String id)
    {
        return useCase.listInstances(id);
    }

    @NonNull
    private final ListInstancesUseCase useCase;
}

package com.yourcompany.personalcrm.fieldtypes.listfieldtypes;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ListFieldTypesUseCase
{
    @NonNull
    private final ListFieldTypesRepository repository;

    public List<FieldTypeSummary> execute()
    {
        return repository.listFieldTypes();
    }
}
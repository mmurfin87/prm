package com.yourcompany.personalcrm.interactiontypes.listinteractiontypes;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.interactiontypes.InteractionType;

@Service
@AllArgsConstructor
public class ListInteractionTypesUseCase
{
    public List<InteractionType> execute()
    {
        return repository.findAll();
    }

    @NonNull
    private final ListInteractionTypesRepository repository;
}
package com.yourcompany.personalcrm.interactiontypes.deleteinteractiontype;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class DeleteInteractionTypeUseCase
{
    public void execute(@NonNull final String id)
    {
        if (repository.delete(id) < 1)
            throw new IllegalArgumentException("Interaction type not found");
    }

    @NonNull
    private final DeleteInteractionTypeRepository repository;
}
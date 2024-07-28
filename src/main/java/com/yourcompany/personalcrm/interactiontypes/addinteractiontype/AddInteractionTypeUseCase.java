package com.yourcompany.personalcrm.interactiontypes.addinteractiontype;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.interactiontypes.InteractionType;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class AddInteractionTypeUseCase
{
    @NonNull
    private final AddInteractionTypeRepository repository;

    public InteractionType execute(@NonNull final String name)
    {
        if (repository.exists(name) > 0)
            throw new IllegalArgumentException("Duplicate name");
        return new InteractionType(repository.insert(name), name);
    }
}
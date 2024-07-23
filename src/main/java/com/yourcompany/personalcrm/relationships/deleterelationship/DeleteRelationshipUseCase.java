package com.yourcompany.personalcrm.relationships.deleterelationship;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class DeleteRelationshipUseCase
{
    public void execute(@NonNull final String id)
    {
        if (repository.deleteId(id) < 1)
            throw new NotFoundException();
    }

    private final DeleteRelationshipRepository repository;
}

package com.yourcompany.personalcrm.relationshiptypes.deleterelationshiptype;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class DeleteRelationshipTypeUseCase
{
    @NonNull
    private final DeleteRelationshipTypeRepository repository;

    public void execute(String id)
    {
        final int deletedCount = repository.delete(id);
        if (deletedCount == 0)
        {
            throw new NotFoundException();
        }
    }
}
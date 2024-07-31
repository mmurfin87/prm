package com.yourcompany.personalcrm.relationshiptypes.deleterelationshiptype;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.NotFoundException;
import com.yourcompany.personalcrm.login.User;

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
        repository.clearCounterpartReferences(User.identify(), id);
        final int deletedCount = repository.delete(User.identify(), id);
        if (deletedCount == 0)
        {
            throw new NotFoundException();
        }
    }
}
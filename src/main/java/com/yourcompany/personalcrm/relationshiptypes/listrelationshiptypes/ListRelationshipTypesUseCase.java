package com.yourcompany.personalcrm.relationshiptypes.listrelationshiptypes;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.login.User;
import com.yourcompany.personalcrm.relationshiptypes.RelationshipType;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Service
@AllArgsConstructor
public class ListRelationshipTypesUseCase
{
    @NonNull
    private final ListRelationshipTypesRepository repository;

    public List<RelationshipType> execute()
    {
        return repository.findAll(User.identify());
    }
}
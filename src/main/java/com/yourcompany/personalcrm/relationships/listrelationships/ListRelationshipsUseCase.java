package com.yourcompany.personalcrm.relationships.listrelationships;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ListRelationshipsUseCase
{
    public List<Relationship> execute()
    {
        return repository.findAll();
    }

    private final ListRelationshipsRepository repository;
}

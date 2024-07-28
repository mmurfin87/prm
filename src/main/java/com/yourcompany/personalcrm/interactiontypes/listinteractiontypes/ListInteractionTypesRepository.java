package com.yourcompany.personalcrm.interactiontypes.listinteractiontypes;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import com.yourcompany.personalcrm.interactiontypes.InteractionType;

import java.util.List;

public interface ListInteractionTypesRepository
{
    @SqlQuery("SELECT id, name FROM interaction_types ORDER BY name")
    @RegisterConstructorMapper(InteractionType.class)
    List<InteractionType> findAll();
}
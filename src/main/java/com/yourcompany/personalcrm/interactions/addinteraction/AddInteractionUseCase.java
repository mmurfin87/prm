package com.yourcompany.personalcrm.interactions.addinteraction;

import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AddInteractionUseCase
{
    @NonNull
    private final AddInteractionRepository repository;

    public Map<String, String> identifyAvailableContacts()
    {
        final Map<String, String> result = repository.getContactIdNameMap();
        log.info("Results: {}", result);
        return result;
    }

    public AddInteractionResponse execute(@NonNull final AddInteractionRequest request)
    {
		if (request.ended != null && request.ended.isBefore(request.started))
			throw new IllegalArgumentException("End time cannot be before start time");
        final String id = repository.insert(request);
        return new AddInteractionResponse(id);
    }
}
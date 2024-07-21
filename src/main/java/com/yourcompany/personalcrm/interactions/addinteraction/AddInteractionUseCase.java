package com.yourcompany.personalcrm.interactions.addinteraction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public AddInteractionForm prepareAddInteractionForm(String forContactId)
    {
        final Map<String, String> result = repository.getContactIdNameMap();
        return new AddInteractionForm(result.entrySet().stream()
                .map(e -> new AddInteractionContactOption(e.getKey(), e.getValue(), e.getKey().equalsIgnoreCase(forContactId)))
                .toList(),
            null,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
            null,
            null, 
            null);
    }

    public AddInteractionResponse execute(@NonNull final AddInteractionRequest request)
    {
		if (request.ended != null && request.ended.isBefore(request.started))
			throw new IllegalArgumentException("End time cannot be before start time");
        final String id = repository.insert(request);
        return new AddInteractionResponse(id);
    }
}
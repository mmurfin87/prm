package com.yourcompany.personalcrm.interactions.deleteinteraction;

import org.springframework.stereotype.Service;

import com.yourcompany.personalcrm.login.User;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class DeleteInteractionUseCase
{
    @NonNull
    private final DeleteInteractionRepository repository;

    public void execute(String id)
    {
        int deletedCount = repository.deleteById(User.identify(), id);
        if (deletedCount == 0)
            throw new IllegalArgumentException("Interaction not found");
    }
}
package com.yourcompany.personalcrm.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yourcompany.personalcrm.NotFoundException;

import lombok.NonNull;

@RestControllerAdvice
public class NotFoundExceptionHandlerAdvice
{
    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(@NonNull final NotFoundException e)
    {
        return ResponseEntity.notFound().build();
    }
}

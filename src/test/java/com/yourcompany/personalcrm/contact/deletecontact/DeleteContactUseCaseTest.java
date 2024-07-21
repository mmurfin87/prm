package com.yourcompany.personalcrm.contact.deletecontact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yourcompany.personalcrm.contacts.deletecontact.DeleteContactRepository;
import com.yourcompany.personalcrm.contacts.deletecontact.DeleteContactUseCase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteContactUseCaseTest
{
    @Mock
    private DeleteContactRepository mockRepository;
    @InjectMocks
    private DeleteContactUseCase deleteContactUseCase;

    @Test
    void execute_WhenContactExists_ShouldReturnSuccessResponse()
    {
        // Arrange
        String contactId = "existingId";
        when(mockRepository.deleteContact(contactId)).thenReturn(true);

        // Act
        deleteContactUseCase.execute(contactId);

        // Assert
        verify(mockRepository).deleteContact(contactId);
    }

    @Test
    void execute_WhenContactDoesNotExist_ShouldReturnFailureResponse()
    {
        // Arrange
        String contactId = "nonExistingId";
        when(mockRepository.deleteContact(contactId)).thenReturn(false);

        // Act
        assertThrows(IllegalArgumentException.class, () -> deleteContactUseCase.execute(contactId));

        // Assert
        verify(mockRepository).deleteContact(contactId);
    }
}
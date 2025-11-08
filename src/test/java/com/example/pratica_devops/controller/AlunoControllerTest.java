package com.example.pratica_devops.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pratica_devops.DTO.AlunoDTO;
import com.example.pratica_devops.service.AlunoService;

public class AlunoControllerTest {

    @Mock
    private AlunoService alunoService;

    @InjectMocks
    private AlunoController alunoController;
    
    @Test
    void testGetAlunos() {
        // Arrange
        List<AlunoDTO> expectedDTOs = Arrays.asList(
            new AlunoDTO(1L, true, true),
            new AlunoDTO(2L, false, true)
        );
        when(alunoService.getAllAlunos()).thenReturn(expectedDTOs);

        // Act
        List<AlunoDTO> actualDTOs = alunoController.getAlunos();

        // Assert
        assertEquals(expectedDTOs, actualDTOs, "Deve retornar a lista de DTOs do serviço");
        verify(alunoService).getAllAlunos();
    }
}
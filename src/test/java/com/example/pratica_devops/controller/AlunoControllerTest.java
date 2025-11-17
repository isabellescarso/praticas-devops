package com.example.pratica_devops.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pratica_devops.DTO.AlunoDTO;
import com.example.pratica_devops.service.AlunoService;

@ExtendWith(MockitoExtension.class)
public class AlunoControllerTest {

    @Mock
    private AlunoService alunoService;

    @InjectMocks
    private AlunoController alunoController;
    
    @Test
    void testGetAlunos_DeveRetornarListaDeAlunos() {
        // Arrange
        List<AlunoDTO> expectedDTOs = Arrays.asList(
            new AlunoDTO(1L, "nome1", true, true),
            new AlunoDTO(2L, "nome2", false, true)
        );
        when(alunoService.getAllAlunos()).thenReturn(expectedDTOs);

        // Act
        List<AlunoDTO> actualDTOs = alunoController.getAlunos();

        // Assert
        assertNotNull(actualDTOs);
        assertEquals(2, actualDTOs.size());
        assertEquals(expectedDTOs, actualDTOs);
        verify(alunoService, times(1)).getAllAlunos();
    }

    @Test
    void testGetAlunos_DeveRetornarListaVazia() {
        // Arrange
        when(alunoService.getAllAlunos()).thenReturn(Collections.emptyList());

        // Act
        List<AlunoDTO> actualDTOs = alunoController.getAlunos();

        // Assert
        assertNotNull(actualDTOs);
        assertEquals(0, actualDTOs.size());
        verify(alunoService, times(1)).getAllAlunos();
    }

    @Test
    void testGetAlunos_DeveRetornarListaComUmAluno() {
        // Arrange
        List<AlunoDTO> expectedDTOs = Arrays.asList(
            new AlunoDTO(1L, "aluno", true, true)
        );
        when(alunoService.getAllAlunos()).thenReturn(expectedDTOs);

        // Act
        List<AlunoDTO> actualDTOs = alunoController.getAlunos();

        // Assert
        assertNotNull(actualDTOs);
        assertEquals(1, actualDTOs.size());
        assertEquals(true, actualDTOs.get(0).isAssinaturaPremium());
        assertEquals(true, actualDTOs.get(0).isAssinaturaAtiva());
        verify(alunoService, times(1)).getAllAlunos();
    }

    @Test
    void testCreateAluno_DeveRetornarAlunoComStatus201() {
        // Arrange
        AlunoDTO inputDTO = new AlunoDTO(null, "aluno nome", true, true);
        AlunoDTO expectedDTO = new AlunoDTO(1L, "aluno nome", true, true);
        when(alunoService.createAluno(inputDTO)).thenReturn(expectedDTO);

        // Act
        var response = alunoController.createAluno(inputDTO);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("aluno nome", response.getBody().getNome());
        verify(alunoService, times(1)).createAluno(inputDTO);
    }
}



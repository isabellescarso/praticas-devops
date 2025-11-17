package com.example.pratica_devops.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pratica_devops.DTO.AlunoDTO;
import com.example.pratica_devops.domain.Aluno;
import com.example.pratica_devops.domain.Assinatura;
import com.example.pratica_devops.repository.AlunoRepository;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void testGetAllAlunos_DeveRetornarListaDeAlunosDTO() {
        // Arrange
        Assinatura assinatura1 = new Assinatura(true, true);
        Assinatura assinatura2 = new Assinatura(false, true);
        
        Aluno aluno1 = new Aluno(assinatura1);
        Aluno aluno2 = new Aluno(assinatura2);
        
        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno1, aluno2));

        // Act
        List<AlunoDTO> result = alunoService.getAllAlunos();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAlunos_DeveRetornarListaVazia() {
        // Arrange
        when(alunoRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<AlunoDTO> result = alunoService.getAllAlunos();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAlunos_DeveConverterCorretamenteEntidadeParaDTO() {
        // Arrange
        Assinatura assinatura = new Assinatura(true, true);
        Aluno aluno = new Aluno(assinatura);
        
        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno));

        // Act
        List<AlunoDTO> result = alunoService.getAllAlunos();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(true, result.get(0).isAssinaturaPremium());
        assertEquals(true, result.get(0).isAssinaturaAtiva());
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAlunos_DeveConverterMultiplosAlunosComDiferentesAssinaturas() {
        // Arrange
        Assinatura assinaturaPremium = new Assinatura(true, true);
        Assinatura assinaturaNaoPremium = new Assinatura(false, true);
        Assinatura assinaturaInativa = new Assinatura(true, false);
        
        Aluno aluno1 = new Aluno(assinaturaPremium);
        Aluno aluno2 = new Aluno(assinaturaNaoPremium);
        Aluno aluno3 = new Aluno(assinaturaInativa);
        
        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno1, aluno2, aluno3));

        // Act
        List<AlunoDTO> result = alunoService.getAllAlunos();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verifica aluno1 - premium e ativa
        assertEquals(true, result.get(0).isAssinaturaPremium());
        assertEquals(true, result.get(0).isAssinaturaAtiva());
        
        // Verifica aluno2 - não premium mas ativa
        assertEquals(false, result.get(1).isAssinaturaPremium());
        assertEquals(true, result.get(1).isAssinaturaAtiva());
        
        // Verifica aluno3 - premium mas inativa
        assertEquals(true, result.get(2).isAssinaturaPremium());
        assertEquals(false, result.get(2).isAssinaturaAtiva());
        
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void testCreateAluno_DeveSalvarERetornarDTO() {
        // Arrange
        AlunoDTO inputDTO = new AlunoDTO(null, "aluno", true, true);
        Aluno alunoSaved = new Aluno("aluno", true);
        alunoSaved.setId(1L);
        alunoSaved.setAssinaturaAtiva(true);

        when(alunoRepository.save(org.mockito.ArgumentMatchers.any(Aluno.class))).thenReturn(alunoSaved);

        // Act
        AlunoDTO result = alunoService.createAluno(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("aluno", result.getNome());
        assertTrue(result.isAssinaturaPremium());
        assertTrue(result.isAssinaturaAtiva());
        verify(alunoRepository, times(1)).save(org.mockito.ArgumentMatchers.any(Aluno.class));
    }
}

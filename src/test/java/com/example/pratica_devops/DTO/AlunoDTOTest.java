package com.example.pratica_devops.DTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.pratica_devops.domain.Aluno;

public class AlunoDTOTest {

    @Test
    void testFromEntity() {
        // Arrange
        Aluno aluno = new Aluno(true);
        aluno.setId(1L);

        // Act
        AlunoDTO dto = AlunoDTO.fromEntity(aluno);

        // Assert
        assertEquals(1L, dto.getId(), "ID deve ser igual ao do aluno");
        assertTrue(dto.isAssinaturaPremium(), "Premium deve ser igual ao do aluno");
        assertTrue(dto.isAssinaturaAtiva(), "Status da assinatura deve ser igual ao do aluno");
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        AlunoDTO dto = new AlunoDTO();

        // Act & Assert
        Long id = 1L;
        dto.setId(id);
        assertEquals(id, dto.getId(), "Getter/Setter de ID deve funcionar corretamente");

        dto.setAssinaturaPremium(true);
        assertTrue(dto.isAssinaturaPremium(), "Getter/Setter de premium deve funcionar corretamente");

        dto.setAssinaturaAtiva(true);
        assertTrue(dto.isAssinaturaAtiva(), "Getter/Setter de assinatura ativa deve funcionar corretamente");
    }

    @Test
    void testConstructorWithParameters() {
        // Arrange & Act
        Long id = 1L;
        boolean premium = true;
        boolean ativa = true;
        AlunoDTO dto = new AlunoDTO(id, premium, ativa);

        // Assert
        assertEquals(id, dto.getId(), "ID deve ser configurado corretamente pelo construtor");
        assertTrue(dto.isAssinaturaPremium(), "Premium deve ser configurado corretamente pelo construtor");
        assertTrue(dto.isAssinaturaAtiva(), "Status da assinatura deve ser configurado corretamente pelo construtor");
    }

    @Test
    void testFromEntityWithNullAssinatura() {
        // Arrange
        Aluno aluno = new Aluno(true);
        aluno.setId(1L);
        aluno.setAssinatura(null);

        // Act
        AlunoDTO dto = AlunoDTO.fromEntity(aluno);

        // Assert
        assertEquals(1L, dto.getId(), "ID deve ser igual ao do aluno");
        assertFalse(dto.isAssinaturaPremium(), "Premium deve ser falso quando assinatura é nula");
        assertFalse(dto.isAssinaturaAtiva(), "Status da assinatura deve ser falso quando assinatura é nula");
    }
}
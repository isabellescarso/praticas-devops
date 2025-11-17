package com.example.pratica_devops;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.pratica_devops.domain.Aluno;
import com.example.pratica_devops.domain.Assinatura;
import com.example.pratica_devops.domain.CodigoModulo;
import com.example.pratica_devops.domain.Modulo;

public class AlunoTest {

    @Test
    void dadoAlunoComPremiumAtivo_quandoCursandoModuloValido_entaoDeveSerElegivel() {
        // Arrange
        Aluno aluno = new Aluno(true);
        Modulo modulo = new Modulo("M1", true);

        // Act
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);

        // Assert
        assertTrue(elegivel, "Aluno com premium ativo deveria ser elegível para projetos reais.");
    }

    @Test
    void dadoAlunoSemPremium_quandoCursandoModuloValido_entaoNaoDeveSerElegivel() {
        // Arrange
        Aluno aluno = new Aluno(false);
        Modulo modulo = new Modulo("M1", true);

        // Act
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);

        // Assert
        assertFalse(elegivel, "Aluno sem premium não deveria ser elegível para projetos reais.");
    }

    @Test
    void dadoAlunoPremiumComAssinaturaInativa_quandoCursandoModuloValido_entaoNaoDeveSerElegivel() {
        // Arrange
        Aluno aluno = new Aluno(true);
        aluno.setAssinaturaAtiva(false);
        Modulo modulo = new Modulo("M1", true);

        // Act
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);

        // Assert
        assertFalse(elegivel, "Aluno premium com assinatura inativa não deveria ser elegível.");
    }
    
    @Test
    void dadoAlunoPremium_quandoModuloNulo_entaoNaoDeveSerElegivel() {
        // Arrange
        Aluno aluno = new Aluno(true);

        // Act
        boolean elegivel = aluno.isElegivelParaProjetosReais(null);

        // Assert
        assertFalse(elegivel, "Aluno premium com módulo nulo não deveria ser elegível.");
    }
    
    @Test
    void dadoAlunoPremium_quandoModuloInvalido_entaoNaoDeveSerElegivel() {
        // Arrange
        Aluno aluno = new Aluno(true);

        // Act - Cenário 1: módulo inválido
        Modulo moduloInvalido = new Modulo("M1", false);
        boolean elegivel1 = aluno.isElegivelParaProjetosReais(moduloInvalido);

        // Act - Cenário 2: módulo nulo
        boolean elegivel2 = aluno.isElegivelParaProjetosReais(null);

        // Assert
        assertFalse(elegivel1, "Aluno premium com módulo inválido não deveria ser elegível.");
        assertFalse(elegivel2, "Aluno premium com módulo nulo não deveria ser elegível.");
    }

    @Test
    void testConstrutorComAssinatura() {
        // Arrange
        Assinatura assinatura = new Assinatura(true, true);
        
        // Act
        Aluno aluno = new Aluno(assinatura);
        
        // Assert
        assertNotNull(aluno.getAssinatura());
        assertEquals(assinatura, aluno.getAssinatura());
    }

    @Test
    void testGettersESetters() {
        // Arrange
        Aluno aluno = new Aluno(true);
        
        // Act & Assert - ID
        aluno.setId(1L);
        assertEquals(1L, aluno.getId());
        
        // Act & Assert - Assinatura
        Assinatura novaAssinatura = new Assinatura(false, true);
        aluno.setAssinatura(novaAssinatura);
        assertEquals(novaAssinatura, aluno.getAssinatura());
    }

    @Test
    void testSetAssinaturaAtiva_ComAssinaturaExistente() {
        // Arrange
        Aluno aluno = new Aluno(true); // Premium e ativa
        
        // Act
        aluno.setAssinaturaAtiva(false);
        
        // Assert
        assertFalse(aluno.getAssinatura().isAtiva());
        assertTrue(aluno.getAssinatura().isPremium());
    }

    @Test
    void testSetAssinaturaAtiva_SemAssinaturaExistente() {
        // Arrange
        Aluno aluno = new Aluno(new Assinatura(false, false));
        aluno.setAssinatura(null);
        
        // Act
        aluno.setAssinaturaAtiva(true);
        
        // Assert
        assertNotNull(aluno.getAssinatura());
        assertTrue(aluno.getAssinatura().isAtiva());
        assertTrue(aluno.getAssinatura().isPremium());
    }

    @Test
    void testModuloSemCodigo_NaoDeveSerElegivel() {
        // Arrange
        Aluno aluno = new Aluno(true);
        Modulo modulo = new Modulo("TEST", true);
        modulo.setCodigoModulo(null); // Remove o código
        
        // Act
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);
        
        // Assert
        assertFalse(elegivel, "Aluno não deve ser elegível com módulo sem código");
    }

    @Test
    void testModuloComCodigoValido_DeveSerElegivel() {
        // Arrange
        Aluno aluno = new Aluno(true);
        CodigoModulo codigo = new CodigoModulo("M001");
        Modulo modulo = new Modulo(codigo, true);
        
        // Act
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);
        
        // Assert
        assertTrue(elegivel, "Aluno premium com módulo válido e código deve ser elegível");
    }

    @Test
    void testConstrutorComNomeEAssinatura() {
        // Arrange & Act
        Assinatura assinatura = new Assinatura(true, true);
        Aluno aluno = new Aluno("aluno nome", assinatura);
        
        // Assert
        assertEquals("aluno nome", aluno.getNome());
        assertEquals(assinatura, aluno.getAssinatura());
        assertTrue(aluno.getAssinatura().isPremium());
        assertTrue(aluno.getAssinatura().isAtiva());
    }

    @Test
    void testSetNome() {
        // Arrange
        Aluno aluno = new Aluno(true);
        
        // Act
            aluno.setNome("aluno nome");
            
        // Assert
        assertEquals("aluno nome", aluno.getNome());
    }
}

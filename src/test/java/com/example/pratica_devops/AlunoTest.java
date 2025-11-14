package com.example.pratica_devops;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.pratica_devops.domain.Aluno;
import com.example.pratica_devops.domain.Modulo;

public class AlunoTest {

    @Test
    void dadoAlunoComPremiumAtivo_quandoCursandoModuloValido_entaoDeveSerElegivel() {
        // Arrange -> Criação dos objetos Alunos e Modulo
        Aluno aluno = new Aluno(true);
        Modulo modulo = new Modulo("M1", true);

        // Act -> Invocação do metodo que queremos testar
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);

        // Assert -> Verifica se o resultado obtido é igual ao esperado.
        assertTrue(elegivel, "Aluno com premium ativo deveria ser elegível para projetos reais.");
    }

    @Test
    void dadoAlunoSemPremium_quandoCursandoModuloValido_entaoNaoDeveSerElegivel() {
    	// Arrange -> Criação dos objetos Alunos e Modulo
        Aluno aluno = new Aluno(false);
        Modulo modulo = new Modulo("M1", true);

        // Act -> Invocação do metodo que queremos testar
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);

        assertFalse(elegivel, "Aluno sem premium não deveria ser elegível para projetos reais.");
    }

    @Test
    void dadoAlunoPremiumComAssinaturaInativa_quandoCursandoModuloValido_entaoNaoDeveSerElegivel() {
    	// Arrange -> Criação dos objetos Alunos e Modulo
        Aluno aluno = new Aluno(true);
        aluno.setAssinaturaAtiva(false);
        Modulo modulo = new Modulo("M1", true);

     	// Act -> Invocação do metodo que queremos testar
        boolean elegivel = aluno.isElegivelParaProjetosReais(modulo);

     	// Assert -> Verifica se o resultado obtido é igual ao esperado.
        assertFalse(elegivel, "Aluno premium com assinatura inativa não deveria ser elegível.");
    }
    
    @Test
    void dadoAlunoPremium_quandoModuloNulo_entaoNaoDeveSerElegivel() {
    	// Arrange -> Criação dos objetos Alunos e Modulo
        Aluno aluno = new Aluno(true);

     // Act -> Invocação do metodo que queremos testar
        boolean elegivel = aluno.isElegivelParaProjetosReais(null);

     // Assert -> Verifica se o resultado obtido é igual ao esperado.
        assertFalse(elegivel, "Aluno premium com módulo nulo não deveria ser elegível.");
    }
    
    @Test
    void dadoAlunoPremium_quandoModuloInvalido_entaoNaoDeveSerElegivel() {
        Aluno aluno = new Aluno(true);

        // Cenário 1: módulo inválido
        Modulo moduloInvalido = new Modulo("M1", false);
        assertFalse(aluno.isElegivelParaProjetosReais(moduloInvalido),
            "Aluno premium com módulo inválido não deveria ser elegível.");

        // Cenário 2: módulo nulo
        assertFalse(aluno.isElegivelParaProjetosReais(null),
            "Aluno premium com módulo nulo não deveria ser elegível.");
    }


    
}

package com.example.pratica_devops;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.pratica_devops.domain.Aluno;
import com.example.pratica_devops.domain.Modulo;
import com.example.pratica_devops.domain.Assinatura;
import com.example.pratica_devops.domain.CodigoModulo;

public class AlunoTest {

    @Test
    void testConstrutorComAssinatura() {
        Assinatura assinatura = new Assinatura(true, true);
        Aluno aluno = new Aluno(assinatura);
        assertEquals(assinatura, aluno.getAssinatura(), "A assinatura deve ser a mesma passada no construtor");
    }

    @Test
    void testConstrutorComPremium() {
        Aluno aluno = new Aluno(true);
        assertTrue(aluno.getAssinatura().isPremium(), "Assinatura deve ser premium");
        assertTrue(aluno.getAssinatura().isAtiva(), "Assinatura deve estar ativa por padrão");
    }

    @Test
    void testSetAndGetId() {
        Aluno aluno = new Aluno(true);
        Long id = 1L;
        aluno.setId(id);
        assertEquals(id, aluno.getId(), "O ID deve ser o mesmo que foi definido");
    }

    @Test
    void testSetAndGetAssinatura() {
        Aluno aluno = new Aluno(true);
        Assinatura novaAssinatura = new Assinatura(false, true);
        aluno.setAssinatura(novaAssinatura);
        assertEquals(novaAssinatura, aluno.getAssinatura(), "A assinatura deve ser atualizada");
    }

    @Test
    void testSetAssinaturaAtiva_ComAssinaturaExistente() {
        Aluno aluno = new Aluno(false);
        aluno.setAssinaturaAtiva(true);
        assertFalse(aluno.getAssinatura().isPremium(), "Deve manter o status premium original");
        assertTrue(aluno.getAssinatura().isAtiva(), "Deve atualizar o status ativo");
    }

    @Test
    void testModuloNulo() {
        Aluno aluno = new Aluno(true);
        assertFalse(aluno.isElegivelParaProjetosReais(null), 
            "Aluno não deve ser elegível quando módulo é nulo");
    }

    @Test
    void testModuloComCodigoModulo() {
        Aluno aluno = new Aluno(true);
        CodigoModulo codigoModulo = new CodigoModulo("M1");
        Modulo modulo = new Modulo(codigoModulo, true);
        assertTrue(aluno.isElegivelParaProjetosReais(modulo),
            "Aluno deve ser elegível com código de módulo válido");
    }

    @Test
    void testSetAndGetCodigoModulo() {
        Modulo modulo = new Modulo("M1", true);
        CodigoModulo novoCodigoModulo = new CodigoModulo("M2");
        modulo.setCodigoModulo(novoCodigoModulo);
        assertEquals(novoCodigoModulo, modulo.getCodigoModulo(),
            "Código do módulo deve ser atualizado");
        assertEquals("M2", modulo.getCodigo(),
            "Getter de conveniência deve retornar o código correto");
    }

    @Test
    void testModuloSetAndGetValido() {
        Modulo modulo = new Modulo("M1", true);
        assertTrue(modulo.isValido(), "Módulo deve iniciar como válido");
        
        modulo.setValido(false);
        assertFalse(modulo.isValido(), "Módulo deve ser marcado como inválido");
    }

    @Test
    void testModuloSetAndGetId() {
        Modulo modulo = new Modulo("M1", true);
        Long id = 1L;
        modulo.setId(id);
        assertEquals(id, modulo.getId(), "ID do módulo deve ser atualizado corretamente");
    }

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

package com.example.pratica_devops.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.pratica_devops.domain.Aluno;
import com.example.pratica_devops.domain.Assinatura;
import com.example.pratica_devops.domain.CodigoModulo;
import com.example.pratica_devops.domain.Modulo;

public class ElegibilidadeServiceTest {

    private ElegibilidadeService service;
    private Modulo moduloValido;
    private Modulo moduloInvalido;

    @BeforeEach
    void setUp() {
        service = new ElegibilidadeService();
        moduloValido = new Modulo(new CodigoModulo("MOD001"), true);
        moduloInvalido = new Modulo(new CodigoModulo("MOD002"), false);
        service.setModulo(moduloValido);
    }

    @Test
    void testVerificarElegibilidade_AlunoComAssinaturaPremiumAtivaEModuloValido_DeveSerElegivel() {
        // Arrange
        Assinatura assinaturaPremium = new Assinatura(true, true);
        Aluno aluno = new Aluno(assinaturaPremium);

        // Act
        service.verificarElegibilidade(aluno);

        // Assert
        assertTrue(service.isElegivel(aluno), "Aluno com assinatura premium ativa e módulo válido deve ser elegível");
    }

    @Test
    void testVerificarElegibilidade_AlunoSemAssinaturaPremium_NaoDeveSerElegivel() {
        // Arrange
        Assinatura assinaturaNaoPremium = new Assinatura(false, true);
        Aluno aluno = new Aluno(assinaturaNaoPremium);

        // Act
        service.verificarElegibilidade(aluno);

        // Assert
        assertFalse(service.isElegivel(aluno), "Aluno sem assinatura premium não deve ser elegível");
    }

    @Test
    void testVerificarElegibilidade_AlunoComAssinaturaPremiumInativa_NaoDeveSerElegivel() {
        // Arrange
        Assinatura assinaturaInativa = new Assinatura(true, false);
        Aluno aluno = new Aluno(assinaturaInativa);

        // Act
        service.verificarElegibilidade(aluno);

        // Assert
        assertFalse(service.isElegivel(aluno), "Aluno com assinatura premium inativa não deve ser elegível");
    }

    @Test
    void testVerificarElegibilidade_AlunoComModuloInvalido_NaoDeveSerElegivel() {
        // Arrange
        service.setModulo(moduloInvalido);
        Assinatura assinaturaPremium = new Assinatura(true, true);
        Aluno aluno = new Aluno(assinaturaPremium);

        // Act
        service.verificarElegibilidade(aluno);

        // Assert
        assertFalse(service.isElegivel(aluno), "Aluno com módulo inválido não deve ser elegível");
    }

    @Test
    void testLimparLista_DeveRemoverTodosAlunosElegiveis() {
        // Arrange
        Aluno aluno = new Aluno(new Assinatura(true, true));
        service.verificarElegibilidade(aluno);
        assertTrue(service.isElegivel(aluno), "Aluno deve estar na lista antes de limpar");

        // Act
        service.limparLista();

        // Assert
        assertFalse(service.isElegivel(aluno), "Aluno não deve estar na lista após limpar");
    }

    @Test
    void testIsElegivel_AlunoNaoVerificado_DeveRetornarFalse() {
        // Arrange
        Aluno aluno = new Aluno(new Assinatura(true, true));

        // Act & Assert
        assertFalse(service.isElegivel(aluno), "Aluno não verificado não deve estar na lista de elegíveis");
    }

    @Test
    void testSetModulo_DeveAlterarModuloUtilizado() {
        // Arrange
        Aluno aluno = new Aluno(new Assinatura(true, true));
        
        // Act - verifica com módulo válido
        service.setModulo(moduloValido);
        service.verificarElegibilidade(aluno);
        boolean elegivelComModuloValido = service.isElegivel(aluno);

        // Limpa e verifica com módulo inválido
        service.limparLista();
        service.setModulo(moduloInvalido);
        service.verificarElegibilidade(aluno);
        boolean elegivelComModuloInvalido = service.isElegivel(aluno);

        // Assert
        assertTrue(elegivelComModuloValido, "Aluno deve ser elegível com módulo válido");
        assertFalse(elegivelComModuloInvalido, "Aluno não deve ser elegível com módulo inválido");
    }
}

package com.example.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.pratica_devops.domain.Aluno;
import com.example.pratica_devops.service.ElegibilidadeService;
import com.example.pratica_devops.domain.Modulo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class ElegibilidadeSteps {
    private Aluno aluno;
    private Modulo modulo;
    private ElegibilidadeService service = new ElegibilidadeService();

    @Dado("que existe um aluno")
    public void queExisteUmAluno() {
        aluno = new Aluno(false); // valor inicial não importa pois será alterado nos próximos steps
    }

    @E("que o aluno possui uma assinatura premium ativa")
    public void queOAlunoPossuiUmaAssinaturaPremiumAtiva() {
        aluno = new Aluno(true);
        aluno.setAssinaturaAtiva(true);
    }

    @E("que o aluno não possui uma assinatura premium")
    public void queOAlunoNaoPossuiUmaAssinaturaPremium() {
        aluno = new Aluno(false);
        aluno.setAssinaturaAtiva(true);
    }

    @E("que ele está cursando um módulo válido")
    public void queEleEstaCursandoUmModuloValido() {
        modulo = new Modulo("MOD001", true);
        service.setModulo(modulo);
    }

    @Quando("o sistema verificar a elegibilidade para projetos reais")
    public void oSistemaVerificarAElegibilidadeParaProjetosReais() {
        service.verificarElegibilidade(aluno);
    }

    @Então("o aluno deve estar na lista de elegíveis para projetos reais")
    public void oAlunoDeveEstarNaListaDeElegiveisParaProjetosReais() {
        assertTrue(service.isElegivel(aluno));
    }

    @Então("o aluno não deve estar na lista de elegíveis para projetos reais")
    public void oAlunoNaoDeveEstarNaListaDeElegiveisParaProjetosReais() {
        assertFalse(service.isElegivel(aluno));
    }
}

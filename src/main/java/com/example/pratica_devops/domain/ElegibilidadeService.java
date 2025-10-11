package com.example.pratica_devops.domain;

import java.util.ArrayList;
import java.util.List;

public class ElegibilidadeService {
    private List<Aluno> alunosElegiveis = new ArrayList<>();
    private Modulo modulo;

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public void verificarElegibilidade(Aluno aluno) {
        if (aluno.isElegivelParaProjetosReais(modulo)) {
            alunosElegiveis.add(aluno);
        }
    }

    public boolean isElegivel(Aluno aluno) {
        return alunosElegiveis.contains(aluno);
    }

    public void limparLista() {
        alunosElegiveis.clear();
    }
}

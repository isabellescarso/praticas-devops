package com.example.pratica_devops.service;

import java.util.ArrayList;
import java.util.List;

import com.example.pratica_devops.domain.Aluno;
import com.example.pratica_devops.domain.Modulo;

public class ElegibilidadeService {
    private final List<Aluno> alunosElegiveis = new ArrayList<>();
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



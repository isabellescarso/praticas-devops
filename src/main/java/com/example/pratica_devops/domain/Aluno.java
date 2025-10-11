package com.example.pratica_devops.domain;

public class Aluno {
    private boolean premium;
    private boolean assinaturaAtiva;

    public Aluno(boolean premium) {
        this.premium = premium;
        this.assinaturaAtiva = true; // começa como ativa
    }


    public void setAssinaturaAtiva(boolean assinaturaAtiva) {
        this.assinaturaAtiva = assinaturaAtiva;
    }

    public boolean isElegivelParaProjetosReais(Modulo modulo) {
        return possuiAssinaturaValida() && moduloValido(modulo);
    }

    private boolean possuiAssinaturaValida() {
        return premium && assinaturaAtiva;
    }

    private boolean moduloValido(Modulo modulo) {
        return modulo != null && modulo.isValido() && modulo.getCodigo() != null;
    }

}




package com.example.pratica_devops.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Embedded
    private Assinatura assinatura;

    // Construtor padrão necessário para o JPA
    protected Aluno() {}

    public Aluno(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public Aluno(boolean premium) {
        this.assinatura = new Assinatura(premium, true);
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public void setAssinaturaAtiva(boolean ativa) {
        if (this.assinatura != null) {
            this.assinatura = new Assinatura(this.assinatura.isPremium(), ativa);
        } else {
            this.assinatura = new Assinatura(true, ativa);
        }
    }

    // Lógica de negócio
    public boolean isElegivelParaProjetosReais(Modulo modulo) {
        return assinatura.possuiAssinaturaValida() && moduloValido(modulo);
    }

    private boolean moduloValido(Modulo modulo) {
        return modulo != null && modulo.isValido() && modulo.getCodigo() != null;
    }
}

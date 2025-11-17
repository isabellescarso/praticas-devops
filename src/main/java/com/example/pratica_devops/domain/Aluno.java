package com.example.pratica_devops.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Embedded
    private Assinatura assinatura;

    public Aluno(String nome, Assinatura assinatura) {
        this.nome = nome;
        this.assinatura = assinatura;
    }

    public Aluno(String nome, boolean premium) {
        this.nome = nome;
        this.assinatura = new Assinatura(premium, true);
    }

    public Aluno(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public Aluno(boolean premium) {
        this.assinatura = new Assinatura(premium, true);
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

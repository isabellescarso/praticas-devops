package com.example.pratica_devops.domain;

import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class Assinatura {

    private boolean premium;
    private boolean ativa;

    // Construtor padrão necessário para o JPA
    protected Assinatura() {}

    public Assinatura(boolean premium, boolean ativa) {
        this.premium = premium;
        this.ativa = ativa;
    }

    public boolean isPremium() {
        return premium;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public boolean possuiAssinaturaValida() {
        return premium && ativa;
    }

    // Sobrescreve equals e hashCode para garantir comparação por valor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assinatura that = (Assinatura) o;
        return premium == that.premium && ativa == that.ativa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(premium, ativa);
    }
}

package com.example.pratica_devops.domain;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class CodigoModulo {

    @Getter
    @Setter
    private String codigo;

    // Construtor padrão necessário para o JPA
    protected CodigoModulo() {}

    public CodigoModulo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do módulo não pode ser nulo ou vazio");
        }
        this.codigo = codigo;
    }

    // Sobrescreve equals e hashCode para garantir comparação por valor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoModulo that = (CodigoModulo) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format("CodigoModulo{codigo=%s}", codigo);
    }
}

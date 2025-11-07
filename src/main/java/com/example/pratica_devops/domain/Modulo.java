package com.example.pratica_devops.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_modulo")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean valido;

    @Embedded
    private CodigoModulo codigoModulo;

    // Construtor padrão necessário para o JPA
    protected Modulo() {}

    public Modulo(CodigoModulo codigoModulo, boolean valido) {
        this.codigoModulo = codigoModulo;
        this.valido = valido;
    }

    public Modulo(String codigo, boolean valido) {
        this.codigoModulo = new CodigoModulo(codigo);
        this.valido = valido;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public CodigoModulo getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(CodigoModulo codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    // Conveniência para acessar diretamente o código
    public String getCodigo() {
        return codigoModulo != null ? codigoModulo.getCodigo() : null;
    }
}

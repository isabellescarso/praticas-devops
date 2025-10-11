package com.example.pratica_devops.domain;


public class Modulo {
    private String codigo;
    private boolean valido;

    public Modulo(String codigo, boolean valido) {
        this.codigo = codigo;
        this.valido = valido;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isValido() {
        return valido;
    }
}



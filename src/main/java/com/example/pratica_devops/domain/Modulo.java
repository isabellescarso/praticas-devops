package com.example.pratica_devops.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_modulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean valido;

    @Embedded
    private CodigoModulo codigoModulo;

    public Modulo(CodigoModulo codigoModulo, boolean valido) {
        this.codigoModulo = codigoModulo;
        this.valido = valido;
    }

    public Modulo(String codigo, boolean valido) {
        this.codigoModulo = new CodigoModulo(codigo);
        this.valido = valido;
    }

    // Conveniência para acessar diretamente o código
    public String getCodigo() {
        return codigoModulo != null ? codigoModulo.getCodigo() : null;
    }
}

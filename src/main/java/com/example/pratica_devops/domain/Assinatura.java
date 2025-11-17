package com.example.pratica_devops.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Assinatura {

    private boolean premium;
    private boolean ativa;

    public boolean possuiAssinaturaValida() {
        return premium && ativa;
    }
}

package com.example.pratica_devops.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CodigoModuloTest {

    @Test
    void testEqualsAndHashCode() {
        CodigoModulo codigo1 = new CodigoModulo("M1");
        CodigoModulo codigo2 = new CodigoModulo("M1");
        CodigoModulo codigo3 = new CodigoModulo("M2");

        assertEquals(codigo1, codigo2, "Códigos iguais devem ser equals");
        assertNotEquals(codigo1, codigo3, "Códigos diferentes não devem ser equals");
        assertEquals(codigo1.hashCode(), codigo2.hashCode(), "Hash codes de códigos iguais devem ser iguais");
        assertNotEquals(codigo1.hashCode(), codigo3.hashCode(), "Hash codes de códigos diferentes não devem ser iguais");
    }

    @Test
    void testToString() {
        CodigoModulo codigoModulo = new CodigoModulo("M1");
        String str = codigoModulo.toString();
        assertTrue(str.contains("codigo=M1"), "toString deve conter o código");
    }
}


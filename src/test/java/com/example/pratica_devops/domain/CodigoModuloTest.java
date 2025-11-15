package com.example.pratica_devops.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CodigoModuloTest {

    @Test
    void testConstrutorComCodigoValido() {
        CodigoModulo codigo = new CodigoModulo("MOD001");
        assertNotNull(codigo);
        assertEquals("MOD001", codigo.getCodigo());
    }

    @Test
    void testConstrutorComCodigoNulo_DeveLancarExcecao() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CodigoModulo(null);
        });
        assertEquals("Código do módulo não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    void testConstrutorComCodigoVazio_DeveLancarExcecao() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CodigoModulo("");
        });
        assertEquals("Código do módulo não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    void testConstrutorComCodigoApenasEspacos_DeveLancarExcecao() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CodigoModulo("   ");
        });
        assertEquals("Código do módulo não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    void testEqualsComMesmoObjeto() {
        CodigoModulo codigo = new CodigoModulo("M1");
        assertEquals(codigo, codigo, "Objeto deve ser igual a si mesmo");
    }

    @Test
    void testEqualsComObjetoNulo() {
        CodigoModulo codigo = new CodigoModulo("M1");
        assertFalse(codigo.equals(null), "Objeto não deve ser igual a null");
    }

    @Test
    void testEqualsComClasseDiferente() {
        CodigoModulo codigo = new CodigoModulo("M1");
        String outraClasse = "M1";
        assertFalse(codigo.equals(outraClasse), "Objeto não deve ser igual a objeto de outra classe");
    }

    @Test
    void testEqualsComCodigosIguais() {
        CodigoModulo codigo1 = new CodigoModulo("M1");
        CodigoModulo codigo2 = new CodigoModulo("M1");
        assertEquals(codigo1, codigo2, "Códigos iguais devem ser equals");
    }

    @Test
    void testEqualsComCodigosDiferentes() {
        CodigoModulo codigo1 = new CodigoModulo("M1");
        CodigoModulo codigo3 = new CodigoModulo("M2");
        assertNotEquals(codigo1, codigo3, "Códigos diferentes não devem ser equals");
    }

    @Test
    void testHashCodeComCodigosIguais() {
        CodigoModulo codigo1 = new CodigoModulo("M1");
        CodigoModulo codigo2 = new CodigoModulo("M1");
        assertEquals(codigo1.hashCode(), codigo2.hashCode(), "Hash codes de códigos iguais devem ser iguais");
    }

    @Test
    void testHashCodeComCodigosDiferentes() {
        CodigoModulo codigo1 = new CodigoModulo("M1");
        CodigoModulo codigo3 = new CodigoModulo("M2");
        assertNotEquals(codigo1.hashCode(), codigo3.hashCode(), "Hash codes de códigos diferentes não devem ser iguais");
    }

    @Test
    void testToString() {
        CodigoModulo codigoModulo = new CodigoModulo("M1");
        String str = codigoModulo.toString();
        assertTrue(str.contains("codigo=M1"), "toString deve conter o código");
        assertTrue(str.contains("CodigoModulo"), "toString deve conter o nome da classe");
    }

    @Test
    void testGetterESetter() {
        CodigoModulo codigo = new CodigoModulo("INICIAL");
        assertEquals("INICIAL", codigo.getCodigo());
        
        codigo.setCodigo("MODIFICADO");
        assertEquals("MODIFICADO", codigo.getCodigo());
    }
}


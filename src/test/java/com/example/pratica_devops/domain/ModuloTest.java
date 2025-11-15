package com.example.pratica_devops.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ModuloTest {

    @Test
    void testConstrutorComCodigoModuloEValido() {
        CodigoModulo codigo = new CodigoModulo("MOD001");
        Modulo modulo = new Modulo(codigo, true);
        
        assertTrue(modulo.isValido(), "Módulo deve ser válido");
        assertNotNull(modulo.getCodigo(), "Código não deve ser nulo");
        assertEquals(codigo, modulo.getCodigoModulo(), "Código deve ser o mesmo fornecido");
    }

    @Test
    void testConstrutorComStringEValido() {
        Modulo modulo = new Modulo("MOD002", true);
        
        assertTrue(modulo.isValido(), "Módulo deve ser válido");
        assertNotNull(modulo.getCodigo(), "Código não deve ser nulo");
        assertEquals("MOD002", modulo.getCodigo(), "Código string deve ser convertido");
    }

    @Test
    void testConstrutorComStringEInvalido() {
        Modulo modulo = new Modulo("MOD003", false);
        
        assertFalse(modulo.isValido(), "Módulo deve ser inválido");
        assertNotNull(modulo.getCodigo(), "Código não deve ser nulo");
    }

    @Test
    void testGettersESetters() {
        Modulo modulo = new Modulo("TEST", false);
        
        modulo.setId(1L);
        assertEquals(1L, modulo.getId(), "ID deve ser 1");
        
        modulo.setValido(true);
        assertTrue(modulo.isValido(), "Módulo deve ser válido após setValido(true)");
        
        CodigoModulo codigo = new CodigoModulo("NEWCODE");
        modulo.setCodigoModulo(codigo);
        assertEquals(codigo, modulo.getCodigoModulo(), "Código deve ser o definido");
    }

    @Test
    void testSetValidoComDiferentesValores() {
        Modulo modulo = new Modulo("MOD001", true);
        assertTrue(modulo.isValido());
        
        modulo.setValido(false);
        assertFalse(modulo.isValido(), "Módulo deve ser inválido após setValido(false)");
        
        modulo.setValido(true);
        assertTrue(modulo.isValido(), "Módulo deve ser válido novamente");
    }

    @Test
    void testModuloComCodigoNulo() {
        Modulo modulo = new Modulo("TEMP", true);
        assertNotNull(modulo.getCodigo(), "Código não deve ser nulo inicialmente");
        
        modulo.setCodigoModulo(null);
        assertNull(modulo.getCodigo(), "Código deve ser nulo após set null");
        
        CodigoModulo novoCodigo = new CodigoModulo("NEW");
        modulo.setCodigoModulo(novoCodigo);
        assertNotNull(modulo.getCodigo(), "Código não deve mais ser nulo");
    }

    @Test
    void testGetCodigoRetornaNullQuandoCodigoModuloNulo() {
        Modulo modulo = new Modulo("TEST", true);
        modulo.setCodigoModulo(null);
        
        assertNull(modulo.getCodigo(), "getCodigo() deve retornar null quando codigoModulo é null");
    }
}

package com.example.pratica_devops.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AssinaturaTest {

    @Test
    void testConstrutorPadrao() {
        Assinatura assinatura = new Assinatura();
        assertFalse(assinatura.isPremium(), "Premium deve ser falso por padrão");
        assertFalse(assinatura.isAtiva(), "Ativa deve ser falso por padrão");
    }

    @Test
    void testConstrutorComParametros() {
        Assinatura assinatura = new Assinatura(true, true);
        assertTrue(assinatura.isPremium(), "Premium deve ser true quando especificado");
        assertTrue(assinatura.isAtiva(), "Ativa deve ser true quando especificado");
    }

    @Test
    void testGetters() {
        Assinatura assinatura = new Assinatura(true, true);
        assertTrue(assinatura.isPremium(), "isPremium deve retornar o valor correto");
        assertTrue(assinatura.isAtiva(), "isAtiva deve retornar o valor correto");
    }

    @Test
    void testPossuiAssinaturaValida() {
        Assinatura assinatura1 = new Assinatura(true, true);
        assertTrue(assinatura1.possuiAssinaturaValida(), "Assinatura premium e ativa deve ser válida");

        Assinatura assinatura2 = new Assinatura(true, false);
        assertFalse(assinatura2.possuiAssinaturaValida(), "Assinatura premium inativa não deve ser válida");

        Assinatura assinatura3 = new Assinatura(false, true);
        assertFalse(assinatura3.possuiAssinaturaValida(), "Assinatura não premium não deve ser válida");

        Assinatura assinatura4 = new Assinatura(false, false);
        assertFalse(assinatura4.possuiAssinaturaValida(), "Assinatura não premium e inativa não deve ser válida");
    }

    @Test
    void testEqualsAndHashCode() {
        Assinatura assinatura1 = new Assinatura(true, true);
        Assinatura assinatura2 = new Assinatura(true, true);
        Assinatura assinatura3 = new Assinatura(false, true);

        assertEquals(assinatura1, assinatura2, "Assinaturas iguais devem ser equals");
        assertNotEquals(assinatura1, assinatura3, "Assinaturas diferentes não devem ser equals");
        assertEquals(assinatura1.hashCode(), assinatura2.hashCode(), "Hash codes de assinaturas iguais devem ser iguais");
        assertNotEquals(assinatura1.hashCode(), assinatura3.hashCode(), "Hash codes de assinaturas diferentes não devem ser iguais");
    }

    @Test
    void testToString() {
        Assinatura assinatura = new Assinatura(true, true);
        String str = assinatura.toString();
        assertTrue(str.contains("premium=true"), "toString deve conter o status premium");
        assertTrue(str.contains("ativa=true"), "toString deve conter o status ativa");
    }
}
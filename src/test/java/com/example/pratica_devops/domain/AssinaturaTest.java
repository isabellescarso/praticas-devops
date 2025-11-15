package com.example.pratica_devops.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void testPossuiAssinaturaValida_PremiumAtiva() {
        Assinatura assinatura = new Assinatura(true, true);
        assertTrue(assinatura.possuiAssinaturaValida(), "Assinatura premium e ativa deve ser válida");
    }

    @Test
    void testPossuiAssinaturaValida_PremiumInativa() {
        Assinatura assinatura = new Assinatura(true, false);
        assertFalse(assinatura.possuiAssinaturaValida(), "Assinatura premium inativa não deve ser válida");
    }

    @Test
    void testPossuiAssinaturaValida_NaoPremiumAtiva() {
        Assinatura assinatura = new Assinatura(false, true);
        assertFalse(assinatura.possuiAssinaturaValida(), "Assinatura não premium não deve ser válida");
    }

    @Test
    void testPossuiAssinaturaValida_NaoPremiumInativa() {
        Assinatura assinatura = new Assinatura(false, false);
        assertFalse(assinatura.possuiAssinaturaValida(), "Assinatura não premium e inativa não deve ser válida");
    }

    @Test
    void testEqualsComMesmoObjeto() {
        Assinatura assinatura = new Assinatura(true, true);
        assertEquals(assinatura, assinatura, "Objeto deve ser igual a si mesmo");
    }

    @Test
    void testEqualsComObjetoNulo() {
        Assinatura assinatura = new Assinatura(true, true);
        assertNotEquals(null, assinatura, "Objeto não deve ser igual a null");
    }

    @Test
    void testEqualsComClasseDiferente() {
        Assinatura assinatura = new Assinatura(true, true);
        Object outraClasse = "String";
        assertNotEquals(assinatura, outraClasse, "Objeto não deve ser igual a objeto de outra classe");
    }

    @Test
    void testEqualsComAssinaturasIguais() {
        Assinatura assinatura1 = new Assinatura(true, true);
        Assinatura assinatura2 = new Assinatura(true, true);
        assertEquals(assinatura1, assinatura2, "Assinaturas iguais devem ser equals");
    }

    @Test
    void testEqualsComAssinaturasDiferentes_Premium() {
        Assinatura assinatura1 = new Assinatura(true, true);
        Assinatura assinatura3 = new Assinatura(false, true);
        assertNotEquals(assinatura1, assinatura3, "Assinaturas com premium diferente não devem ser equals");
    }

    @Test
    void testEqualsComAssinaturasDiferentes_Ativa() {
        Assinatura assinatura1 = new Assinatura(true, true);
        Assinatura assinatura4 = new Assinatura(true, false);
        assertNotEquals(assinatura1, assinatura4, "Assinaturas com ativa diferente não devem ser equals");
    }

    @Test
    void testHashCodeComAssinaturasIguais() {
        Assinatura assinatura1 = new Assinatura(true, true);
        Assinatura assinatura2 = new Assinatura(true, true);
        assertEquals(assinatura1.hashCode(), assinatura2.hashCode(), "Hash codes de assinaturas iguais devem ser iguais");
    }

    @Test
    void testHashCodeComAssinaturasDiferentes() {
        Assinatura assinatura1 = new Assinatura(true, true);
        Assinatura assinatura3 = new Assinatura(false, true);
        assertNotEquals(assinatura1.hashCode(), assinatura3.hashCode(), "Hash codes de assinaturas diferentes não devem ser iguais");
    }

    @Test
    void testToString() {
        Assinatura assinatura = new Assinatura(true, true);
        String str = assinatura.toString();
        assertTrue(str.contains("premium=true"), "toString deve conter o status premium");
        assertTrue(str.contains("ativa=true"), "toString deve conter o status ativa");
        assertTrue(str.contains("Assinatura"), "toString deve conter o nome da classe");
    }
}
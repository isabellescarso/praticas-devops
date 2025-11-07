package com.example.pratica_devops;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PraticaDevopsApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "O contexto da aplicação deve ser carregado");
    }

    @Test
    void mainMethodStartsApplication() {
        System.setProperty("server.port", "0");
        PraticaDevopsApplication.main(new String[] {});
        assertTrue(true, "O método main deve iniciar sem exceções");
        System.clearProperty("server.port");
    }
}

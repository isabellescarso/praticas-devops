package com.example.configuration;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.pratica_devops.PraticaDevopsApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = PraticaDevopsApplication.class)
public class CucumberSpringConfiguration {
}

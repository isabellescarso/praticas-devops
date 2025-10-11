package com.example.steps;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.pratica_devops.PraticaDevopsApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = PraticaDevopsApplication.class)
public class CucumberSpringConfiguration {
}

package com.example.pratica_devops.infrastructure.ai;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import lombok.extern.slf4j.Slf4j;

//Configuração de IA - LangChain4J + OpenAI
@Slf4j
@Configuration
public class AiConfiguration {
    
    @Bean
    @ConditionalOnProperty(name = "openai.api-key")
    public ChatLanguageModel chatLanguageModel(
            @Value("${openai.api-key}") String apiKey,
            @Value("${openai.model:gpt-4o-mini}") String model) {
        
        log.info("Configurando OpenAI: {}", model);
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(model)
                .temperature(0.7)
                .timeout(Duration.ofSeconds(60))
                .build();
    }
    
    @Bean
    @ConditionalOnProperty(name = "openai.api-key")
    public EducationalConsultantAI educationalConsultantAI(ChatLanguageModel model) {
        return AiServices.builder(EducationalConsultantAI.class)
                .chatLanguageModel(model)
                .build();
    }
}

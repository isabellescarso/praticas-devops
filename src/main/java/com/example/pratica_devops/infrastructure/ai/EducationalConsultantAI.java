package com.example.pratica_devops.infrastructure.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

//Interface AI Service - LangChain4J
public interface EducationalConsultantAI {
    
    @SystemMessage("""
            Você é um consultor educacional especializado em tecnologia.
            Recomende 3 cursos baseados no perfil do estudante.
            
            Retorne APENAS um JSON válido no formato:
            [
              {
                "courseName": "Nome do Curso",
                "description": "Descrição do curso",
                "reasoning": "Por que é adequado"
              }
            ]
            """)
    @UserMessage("Estudante: {{name}}, Interesses: {{interests}}, Cursos concluídos: {{completed}}")
    String recommend(String name, String interests, String completed);
}

package com.example.pratica_devops.infrastructure.ai;

import com.example.pratica_devops.domain.CourseRecommendation;
import com.example.pratica_devops.domain.StudentProfile;
import com.example.pratica_devops.domain.service.CourseRecommendationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

//Adapter - Implementação do serviço de recomendação com OpenAI
@Slf4j
@Service
public class OpenAICourseRecommendationAdapter implements CourseRecommendationService {
    
    private final EducationalConsultantAI aiService;
    private final ObjectMapper objectMapper;
    private final boolean isAiEnabled;
    
    public OpenAICourseRecommendationAdapter(
            @Autowired(required = false) EducationalConsultantAI aiService,
            ObjectMapper objectMapper,
            @Value("${openai.api-key:}") String apiKey) {
        
        this.aiService = aiService;
        this.objectMapper = objectMapper;
        this.isAiEnabled = apiKey != null && !apiKey.isBlank() && aiService != null;
        
        log.info("OpenAI Service: {}", isAiEnabled ? "ENABLED" : "DISABLED (usando mock)");
    }
    
    @Override
    public List<CourseRecommendation> recommendCourses(StudentProfile profile) {
        if (!isAiEnabled) {
            return mockRecommendations(profile);
        }
        
        try {
            String interests = String.join(", ", profile.getInterests());
            String completed = profile.getCompletedCourses() != null 
                ? String.join(", ", profile.getCompletedCourses()) 
                : "Nenhum";
            
            String json = aiService.recommend(profile.getStudentName(), interests, completed);
            String cleanJson = json.replaceAll("```json|```", "").trim();
            
            return objectMapper.readValue(cleanJson, new TypeReference<List<CourseRecommendation>>() {});
        } catch (Exception e) {
            log.warn("Erro na IA, usando fallback: {}", e.getMessage());
            return mockRecommendations(profile);
        }
    }
    
    private List<CourseRecommendation> mockRecommendations(StudentProfile profile) {
        return Arrays.asList(
            new CourseRecommendation(
                "Arquitetura de Software Moderna",
                "Clean Architecture, DDD e padrões de design essenciais",
                "Base fundamental para desenvolvimento profissional"
            ),
            new CourseRecommendation(
                "DevOps e CI/CD",
                "Docker, Kubernetes, Jenkins e automação",
                "Essencial para deploy e operações modernas"
            ),
            new CourseRecommendation(
                "IA Generativa com LangChain",
                "Construa aplicações inteligentes com IA",
                "Tecnologia emergente e relevante"
            )
        );
    }
}

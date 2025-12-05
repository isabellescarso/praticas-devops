package com.example.pratica_devops.domain.service;

import java.util.List;

import com.example.pratica_devops.domain.CourseRecommendation;
import com.example.pratica_devops.domain.StudentProfile;

//Domain - Serviço de recomendação de cursos
public interface CourseRecommendationService {
    List<CourseRecommendation> recommendCourses(StudentProfile studentProfile);
}

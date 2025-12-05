package com.example.pratica_devops.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Value Object - Recomendação de curso
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRecommendation {
    private String courseName;
    private String description;
    private String reasoning;
}

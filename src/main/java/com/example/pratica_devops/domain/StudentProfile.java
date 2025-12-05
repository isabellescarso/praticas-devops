package com.example.pratica_devops.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Value Object - Perfil do aluno para recomendação de cursos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {
    private String studentName;
    private List<String> interests;
    private List<String> completedCourses;
}

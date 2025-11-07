package com.example.pratica_devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pratica_devops.domain.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}



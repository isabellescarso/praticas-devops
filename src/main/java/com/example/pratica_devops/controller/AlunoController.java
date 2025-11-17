package com.example.pratica_devops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pratica_devops.DTO.AlunoDTO;
import com.example.pratica_devops.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "API para gerenciamento de alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Lista todos os alunos", description = "Retorna uma lista com todos os alunos cadastrados")
    public List<AlunoDTO> getAlunos() {
        return alunoService.getAllAlunos(); 
    }

    @PostMapping
    @Operation(summary = "Cria um novo aluno", description = "Cadastra um novo aluno no sistema")
    public ResponseEntity<AlunoDTO> createAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO novoAluno = alunoService.createAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }
}



package com.example.pratica_devops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pratica_devops.DTO.AlunoDTO;
import com.example.pratica_devops.service.AlunoService;

@RestController
@CrossOrigin
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<AlunoDTO> getAlunos() {
        return alunoService.getAllAlunos(); // Chama o serviço para obter os dados
    }

}

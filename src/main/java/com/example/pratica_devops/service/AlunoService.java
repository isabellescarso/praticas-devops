package com.example.pratica_devops.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pratica_devops.DTO.AlunoDTO;
import com.example.pratica_devops.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoDTO> getAllAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public AlunoDTO createAluno(AlunoDTO alunoDTO) {
        var aluno = alunoDTO.toEntity();
        var alunoSalvo = alunoRepository.save(aluno);
        return AlunoDTO.fromEntity(alunoSalvo);
    }
}


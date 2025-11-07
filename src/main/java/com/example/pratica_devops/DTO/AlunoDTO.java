package com.example.pratica_devops.DTO;

import com.example.pratica_devops.domain.Aluno;

public class AlunoDTO {

    private Long id;
    private boolean assinaturaPremium;
    private boolean assinaturaAtiva;

    // Construtor vazio
    public AlunoDTO() {}

    // Construtor com todos os parâmetros
    public AlunoDTO(Long id, boolean assinaturaPremium, boolean assinaturaAtiva) {
        this.id = id;
        this.assinaturaPremium = assinaturaPremium;
        this.assinaturaAtiva = assinaturaAtiva;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAssinaturaPremium() {
        return assinaturaPremium;
    }

    public void setAssinaturaPremium(boolean assinaturaPremium) {
        this.assinaturaPremium = assinaturaPremium;
    }

    public boolean isAssinaturaAtiva() {
        return assinaturaAtiva;
    }

    public void setAssinaturaAtiva(boolean assinaturaAtiva) {
        this.assinaturaAtiva = assinaturaAtiva;
    }

    // Método de conversão de Aluno para AlunoDTO
    public static AlunoDTO fromEntity(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());

        if (aluno.getAssinatura() != null) {
            dto.setAssinaturaPremium(aluno.getAssinatura().isPremium());
            dto.setAssinaturaAtiva(aluno.getAssinatura().isAtiva());
        }

        return dto;
    }
}

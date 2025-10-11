# language: pt
Funcionalidade: Elegibilidade para Projetos Reais
  Como um sistema
  Eu quero verificar a elegibilidade dos alunos para projetos reais
  Para garantir que apenas alunos qualificados participem

  Cenário: Aluno com assinatura premium e módulo válido
    Dado que existe um aluno
    E que o aluno possui uma assinatura premium ativa
    E que ele está cursando um módulo válido
    Quando o sistema verificar a elegibilidade para projetos reais
    Então o aluno deve estar na lista de elegíveis para projetos reais

  Cenário: Aluno sem assinatura premium e módulo válido
    Dado que existe um aluno
    E que o aluno não possui uma assinatura premium
    E que ele está cursando um módulo válido
    Quando o sistema verificar a elegibilidade para projetos reais
    Então o aluno não deve estar na lista de elegíveis para projetos reais

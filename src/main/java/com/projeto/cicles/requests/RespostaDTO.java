package com.projeto.cicles.requests;

import com.projeto.cicles.models.Pergunta;
import com.projeto.entities.models.Funcionario;

import java.util.UUID;

public record RespostaDTO(
    UUID id,
    Funcionario funcionario,
    Pergunta pergunta,
    String conteudo
) {
}

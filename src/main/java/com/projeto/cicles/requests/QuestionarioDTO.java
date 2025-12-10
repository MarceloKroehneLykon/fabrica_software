package com.projeto.cicles.requests;

import com.projeto.cicles.models.Pergunta;

import java.util.List;
import java.util.UUID;

public record QuestionarioDTO(
    UUID id,
    String titulo,
    List<Pergunta> perguntas
) {
}

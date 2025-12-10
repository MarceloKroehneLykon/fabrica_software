package com.projeto.cicles.requests;

import com.projeto.cicles.enums.TipoPergunta;

import java.util.UUID;

public record PerguntaDTO(
    UUID id,
    TipoPergunta tipoPergunta,
    String texto
) {
}

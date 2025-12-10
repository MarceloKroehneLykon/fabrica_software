package com.projeto.cicles.requests;

import com.projeto.cicles.enums.Categoria;

import java.util.UUID;

public record PerguntaDTO(
    UUID id,
    Categoria categoria,
    String texto
) {
}

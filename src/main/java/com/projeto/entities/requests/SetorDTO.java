package com.projeto.entities.requests;

import java.util.UUID;

public record SetorDTO(
    UUID id,
    UUID empresaId,
    String nome
) {
}

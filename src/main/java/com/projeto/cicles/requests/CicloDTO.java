package com.projeto.cicles.requests;

import com.projeto.cicles.models.Resposta;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public record CicloDTO(
    UUID id,
    Timestamp dataInicio,
    Timestamp dataFim,
    List<Resposta> respostas
) {
}

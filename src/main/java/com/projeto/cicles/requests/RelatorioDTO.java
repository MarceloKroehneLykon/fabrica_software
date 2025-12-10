package com.projeto.cicles.requests;

import com.projeto.cicles.models.Ciclo;
import com.projeto.entities.models.Setor;

import java.sql.Timestamp;
import java.util.UUID;

public record RelatorioDTO(
    UUID id,
    Setor setor,
    Timestamp dataCriacao,
    Ciclo ciclo
) {

}

package com.projeto.bases.requests;

import com.projeto.bases.models.Cargo;

import java.sql.Timestamp;
import java.util.UUID;

public record SessaoUsuarioDTO(UUID id, String token, String nome, Cargo cargo) {
}

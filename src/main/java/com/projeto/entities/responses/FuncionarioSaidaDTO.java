package com.projeto.entities.responses;

import com.projeto.bases.models.Cargo;
import java.util.UUID;

public record FuncionarioSaidaDTO(
    boolean ativo,
    String nome,
    UUID id,
    String email,
    String cpf,
    Cargo cargo,
    String senha
) {

}

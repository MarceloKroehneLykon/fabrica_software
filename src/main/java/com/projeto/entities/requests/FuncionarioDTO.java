package com.projeto.entities.requests;

import java.util.UUID;

public record FuncionarioDTO(
    boolean ativo,
    String nome,
    UUID id,
    String email,
    String cpf,
    String senha,
    int permissaoId,
    UUID setorId,
    UUID empresaId
) {

}

package com.projeto.entities.requests;

import java.sql.Timestamp;
import java.util.UUID;

public record EmpresaDTO(
    boolean ativo,
    String nome,
    String cpfCnpj,
    String email,
    UUID setorId,
    Timestamp dataCadastro,
    Timestamp dataFuncionamentoIncio,
    Timestamp dataFuncionamentoFim
) {
}

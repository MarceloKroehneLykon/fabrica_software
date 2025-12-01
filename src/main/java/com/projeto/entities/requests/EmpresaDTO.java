package com.projeto.entities.requests;

import java.sql.Timestamp;
import java.util.UUID;

public record EmpresaDTO(
    String nome,
    String cpfCnpj,
    UUID setorId,
    Timestamp dataCadastro,
    Timestamp dataFuncionamentoIncio,
    Timestamp dataFuncionamentoFim
) {
}

package com.projeto.entities.models;

import com.projeto.bases.models.EntidadeBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "empresas")
public class Empresa extends EntidadeBase {
    private String nome;
    private String cpfCnpj;
    private String email;
    private Timestamp dataCadastro;
    private Timestamp dataFuncionamentoIncio;
    private Timestamp dataFuncionamentoFim;
}

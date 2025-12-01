package com.projeto.cicles.models;

import com.projeto.entities.models.Setor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "relatorios")
public class Relatorio {
    @GeneratedValue
    @Id
    private UUID id;
    private Setor setor;
    private Timestamp dataCriacao;
}

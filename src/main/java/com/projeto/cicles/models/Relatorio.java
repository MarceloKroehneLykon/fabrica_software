package com.projeto.cicles.models;

import com.projeto.entities.models.Setor;
import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;
    private Timestamp dataCriacao;
    @ManyToOne
    @JoinColumn(name = "ciclo_id")
    private Ciclo ciclo;
}

package com.projeto.cicles.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ciclos")
public class Ciclo {
    @GeneratedValue
    @Id
    private UUID id;
    private Timestamp dataInicio;
    private Timestamp dataFim;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "ciclos_resposta",
            joinColumns = @JoinColumn(name = "ciclo_id"),
            inverseJoinColumns = @JoinColumn(name = "respostas_id")
    )
    private List<Resposta> respostas;
}

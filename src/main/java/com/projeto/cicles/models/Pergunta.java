package com.projeto.cicles.models;

import com.projeto.cicles.enums.Categoria;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "respostas")
public class Pergunta {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "categoria_id")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String texto;

}

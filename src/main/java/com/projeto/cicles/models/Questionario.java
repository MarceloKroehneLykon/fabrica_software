package com.projeto.cicles.models;

import com.projeto.bases.models.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "questionarios")
public class Questionario extends EntidadeBase {

    private String titulo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "questionarios_perguntas",
            joinColumns = @JoinColumn(name = "questionario_id"),
            inverseJoinColumns = @JoinColumn(name = "pergunta_id")
    )
    private List<Pergunta> perguntas;
}

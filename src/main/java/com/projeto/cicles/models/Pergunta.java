package com.projeto.cicles.models;

import com.projeto.cicles.enums.TipoPergunta;
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

    @Column(name = "tipo_pergunta_id")
    @Enumerated(EnumType.STRING)
    private TipoPergunta tipoPergunta;

    private String texto;

}

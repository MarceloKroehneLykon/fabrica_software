package com.projeto.bases.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class EntidadeBase {

    @GeneratedValue
    @Id
    private UUID id;

    private boolean ativo;

}

package com.projeto.cicles.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.projeto.bases.exceptions.ValidacoesException;
import lombok.Getter;

public enum Categoria {

    MULTI(0,"multi");//TODO: adicionar os tipos certos.

    @Getter
    private final int id;
    private final String descricao;

    Categoria(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Categoria fromString(String descricao) {
        for (Categoria categoria : values()) {
            if (categoria.descricao.contentEquals(descricao)) {
                return categoria;
            }
        }
        throw new ValidacoesException("Descrição inválida: " + descricao);
    }

    public static Categoria fromId(int id) {
        for (Categoria categoria : values()) {
            if (categoria.id == id) {
                return categoria;
            }
        }
        throw new ValidacoesException("ID de tipo de pergunta inválida: " + id);
    }
}

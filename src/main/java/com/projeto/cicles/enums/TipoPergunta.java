package com.projeto.cicles.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.projeto.bases.exceptions.ValidacoesException;
import lombok.Getter;

public enum TipoPergunta {

    MULTI(0,"multi");//TODO: adicionar os tipos certos.

    @Getter
    private final int id;
    private final String descricao;

    TipoPergunta(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoPergunta fromString(String descricao) {
        for (TipoPergunta tipoPergunta : values()) {
            if (tipoPergunta.descricao.contentEquals(descricao)) {
                return tipoPergunta;
            }
        }
        throw new ValidacoesException("Descrição inválida: " + descricao);
    }

    public static TipoPergunta fromId(int id) {
        for (TipoPergunta tipoPergunta : values()) {
            if (tipoPergunta.id == id) {
                return tipoPergunta;
            }
        }
        throw new ValidacoesException("ID de tipo de pergunta inválida: " + id);
    }
}

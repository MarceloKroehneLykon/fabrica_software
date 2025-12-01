package com.projeto.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.projeto.bases.exceptions.ValidacoesException;
import lombok.Getter;

public enum Permissoes {

    ADMIN(0, "ADMIN"),
    GERENTE(1, "GERENTE"),
    FUNCIONARIO(2, "FUNCIONARIO");

    @Getter
    private final int id;
    private final String descricao;

    Permissoes(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Permissoes fromString(String descricao) {
        for (Permissoes permissao : values()) {
            if (permissao.descricao.contentEquals(descricao)) {
                return permissao;
            }
        }
        throw new ValidacoesException("Descrição inválida: " + descricao);
    }

    public static Permissoes fromId(int id) {
        for (Permissoes permissao : values()) {
            if (permissao.id == id) {
                return permissao;
            }
        }
        throw new ValidacoesException("ID de permissão inválido: " + id);
    }
}

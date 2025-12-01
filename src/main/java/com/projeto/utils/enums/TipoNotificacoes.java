package com.projeto.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.projeto.bases.exceptions.ValidacoesException;
import lombok.Getter;

public enum TipoNotificacoes {

    ALERTA(0,"alerta");//TODO: adicionar os tipos certos.

    @Getter
    private final int id;
    private final String descricao;

    TipoNotificacoes(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoNotificacoes fromString(String descricao) {
        for (TipoNotificacoes tipoNotificacoes : values()) {
            if (tipoNotificacoes.descricao.contentEquals(descricao)) {
                return tipoNotificacoes;
            }
        }
        throw new ValidacoesException("Descrição inválida: " + descricao);
    }

    public static TipoNotificacoes fromId(int id) {
        for (TipoNotificacoes tipoNotificacoes : values()) {
            if (tipoNotificacoes.id == id) {
                return tipoNotificacoes;
            }
        }
        throw new ValidacoesException("ID de tipo de notificação inválido: " + id);
    }
}

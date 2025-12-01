package com.projeto.utils.models;

import com.projeto.utils.enums.TipoNotificacoes;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "notificacoes")
public class Notificacao {
    @GeneratedValue
    @Id
    private UUID id;
    private Timestamp dataEnvio;
    private String mensagem;

    @Column(name = "tipo_notificacao_id")
    @Enumerated(EnumType.STRING)
    private TipoNotificacoes tipoNotificacoes;
}

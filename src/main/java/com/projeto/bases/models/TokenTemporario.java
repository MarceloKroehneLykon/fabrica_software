package com.projeto.bases.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tokens_temporarios")
public class TokenTemporario extends EntidadeBase {
    private String email;
    private String codigo;
    private LocalDateTime expirationTime;


}

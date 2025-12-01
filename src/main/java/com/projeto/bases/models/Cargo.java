package com.projeto.bases.models;

import com.projeto.entities.enums.Permissoes;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Data
public class Cargo implements GrantedAuthority {

    public Cargo(int permissaoId){
        this.permissao = Permissoes.fromId(permissaoId);
    }
    @Column(name = "permissao_id")
    @Enumerated(EnumType.STRING)
    private Permissoes permissao;

    @Override
    public String getAuthority() {
        return "ROLE_" + permissao.name();
    }
}

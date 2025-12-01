package com.projeto.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.bases.models.Cargo;
import com.projeto.bases.models.EntidadeBase;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario extends EntidadeBase implements UserDetails {

    private String nome;
    private String email;
    private String cpf;

    @Embedded
    private Cargo cargo;

    public void setCargo(int id){
        this.cargo = new Cargo(id);
    }

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

    @JsonIgnore
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return cargo != null
                ? List.of(cargo)
                : List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isEnabled() {
        return isAtivo();
    }
}

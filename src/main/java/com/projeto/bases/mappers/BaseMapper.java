package com.projeto.bases.mappers;

import com.projeto.bases.models.Cargo;
import com.projeto.entities.models.Empresa;
import com.projeto.entities.models.Funcionario;
import com.projeto.entities.models.Setor;
import org.mapstruct.Mapper;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    default Funcionario fromFuncionarioId(UUID id) {
        if (id == null) return null;
        Funcionario entity = new Funcionario();
        entity.setId(id);
        return entity;
    }

    default UUID toFuncionarioId(Funcionario funcionario) {
        if (funcionario == null) return null;
        return funcionario.getId();
    }

    default Empresa fromEmpresaId(UUID id) {
        if (id == null) return null;
        Empresa entity = new Empresa();
        entity.setId(id);
        return entity;
    }

    default UUID toEmpresaId(Empresa empresa) {
        if (empresa == null) return null;
        return empresa.getId();
    }

    default Setor fromSetorId(UUID id) {
        if (id == null) return null;
        Setor entity = new Setor();
        entity.setId(id);
        return entity;
    }

    default UUID toSetorId(Setor setor) {
        if (setor == null) return null;
        return setor.getId();
    }
}

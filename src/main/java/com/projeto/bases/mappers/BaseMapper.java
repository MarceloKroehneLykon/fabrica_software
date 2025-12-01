package com.projeto.bases.mappers;

import com.projeto.bases.models.Cargo;
import com.projeto.entities.models.Funcionario;
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
}

package com.projeto.entities.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.entities.models.Funcionario;
import com.projeto.entities.requests.FuncionarioDTO;
import com.projeto.entities.responses.FuncionarioSaidaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface FuncionarioMapper {

    FuncionarioSaidaDTO toDTO(Funcionario entity);
    List<FuncionarioSaidaDTO> toListDTO(List<Funcionario> entityList);

    Funcionario toEntity(FuncionarioDTO dto);

}

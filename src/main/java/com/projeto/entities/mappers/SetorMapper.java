package com.projeto.entities.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.entities.models.Empresa;
import com.projeto.entities.models.Setor;
import com.projeto.entities.requests.EmpresaDTO;
import com.projeto.entities.requests.SetorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface SetorMapper {
    Setor toEntity(SetorDTO dto);
    SetorDTO toDTO(Setor entity);
    List<SetorDTO> toListDTO(List<Setor> entity);
}

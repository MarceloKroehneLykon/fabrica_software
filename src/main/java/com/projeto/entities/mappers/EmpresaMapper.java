package com.projeto.entities.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.entities.models.Empresa;
import com.projeto.entities.requests.EmpresaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface EmpresaMapper {
    Empresa toEntity(EmpresaDTO dto);
    EmpresaDTO toDTO(Empresa entity);
    List<EmpresaDTO> toListDTO(List<Empresa> entity);
}

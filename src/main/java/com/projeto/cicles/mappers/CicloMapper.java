package com.projeto.cicles.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.cicles.models.Ciclo;
import com.projeto.cicles.requests.CicloDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface CicloMapper {
    Ciclo toEntity(CicloDTO dto);
    CicloDTO toDTO(Ciclo entity);
    List<CicloDTO> toListDTO(List<Ciclo> entity);
}

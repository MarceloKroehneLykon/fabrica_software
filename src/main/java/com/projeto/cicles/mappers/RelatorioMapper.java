package com.projeto.cicles.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.cicles.models.Relatorio;
import com.projeto.cicles.requests.RelatorioDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface RelatorioMapper {
    Relatorio toEntity(RelatorioDTO dto);
    RelatorioDTO toDTO(Relatorio entity);
    List<RelatorioDTO> toListDTO(List<Relatorio> entity);
}

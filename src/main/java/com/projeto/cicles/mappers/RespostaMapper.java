package com.projeto.cicles.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.cicles.models.Resposta;
import com.projeto.cicles.requests.RespostaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface RespostaMapper {
    Resposta toEntity(RespostaDTO dto);
    RespostaDTO toDTO(Resposta entity);
    List<RespostaDTO> toListDTO(List<Resposta> entity);
}

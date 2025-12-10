package com.projeto.cicles.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.cicles.models.Ciclo;
import com.projeto.cicles.models.Pergunta;
import com.projeto.cicles.requests.CicloDTO;
import com.projeto.cicles.requests.PerguntaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface PerguntaMapper {
    Pergunta toEntity(PerguntaDTO dto);
    PerguntaDTO toDTO(Pergunta entity);
    List<PerguntaDTO> toListDTO(List<Pergunta> entity);
}

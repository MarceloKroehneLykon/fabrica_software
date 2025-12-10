package com.projeto.cicles.mappers;

import com.projeto.bases.mappers.BaseMapper;
import com.projeto.cicles.models.Questionario;
import com.projeto.cicles.requests.QuestionarioDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface QuestionarioMapper {
    Questionario toEntity(QuestionarioDTO dto);
    QuestionarioDTO toDTO(Questionario entity);
    List<QuestionarioDTO> toListDTO(List<Questionario> entity);
}

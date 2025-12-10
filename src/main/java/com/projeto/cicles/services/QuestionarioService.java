package com.projeto.cicles.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.bases.services.BaseService;
import com.projeto.cicles.mappers.QuestionarioMapper;
import com.projeto.cicles.models.Questionario;
import com.projeto.cicles.repositories.QuestionarioRepository;
import com.projeto.cicles.requests.QuestionarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionarioService extends BaseService<Questionario, QuestionarioDTO> {

    public final QuestionarioMapper questionarioMapper;

    @Autowired
    private QuestionarioRepository questionarioRepository;


    public QuestionarioService(QuestionarioMapper questionarioMapper) {
        this.questionarioMapper = questionarioMapper;
    }

    @Override
    public Questionario cadastrar(QuestionarioDTO objetoDTO) {

        Questionario questionario = questionarioMapper.toEntity(objetoDTO);

        return questionarioRepository.save(questionario);
    }

    @Override
    public Questionario editar(QuestionarioDTO objetoDTO, UUID id) {
        Questionario questionario = questionarioMapper.toEntity(objetoDTO);
        questionario.setId(id);

        return questionarioRepository.save(questionario);
    }

    @Override
    public void deletar(UUID id) {
        questionarioRepository.deleteById(id);
    }

    @Override
    public Questionario findById(UUID id) {
        return questionarioRepository.findById(id).orElseThrow(() -> new ValidacoesException("Questionario não encontrado com o ID: " + id));
    }

}

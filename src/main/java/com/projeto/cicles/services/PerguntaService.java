package com.projeto.cicles.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.bases.services.BaseService;
import com.projeto.cicles.mappers.PerguntaMapper;
import com.projeto.cicles.models.Pergunta;
import com.projeto.cicles.repositories.PerguntaRepository;
import com.projeto.cicles.requests.PerguntaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PerguntaService extends BaseService<Pergunta, PerguntaDTO> {

    public final PerguntaMapper perguntaMapper;

    @Autowired
    private PerguntaRepository perguntaRepository;


    public PerguntaService(PerguntaMapper perguntaMapper) {
        this.perguntaMapper = perguntaMapper;
    }

    @Override
    public Pergunta cadastrar(PerguntaDTO objetoDTO) {

        Pergunta pergunta = perguntaMapper.toEntity(objetoDTO);

        return perguntaRepository.save(pergunta);
    }

    @Override
    public Pergunta editar(PerguntaDTO objetoDTO, UUID id) {
        Pergunta pergunta = perguntaMapper.toEntity(objetoDTO);
        pergunta.setId(id);

        return perguntaRepository.save(pergunta);
    }

    @Override
    public void deletar(UUID id) {
        perguntaRepository.deleteById(id);
    }

    @Override
    public Pergunta findById(UUID id) {
        return perguntaRepository.findById(id).orElseThrow(() -> new ValidacoesException("Pergunta não encontrado com o ID: " + id));
    }

}

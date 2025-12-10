package com.projeto.cicles.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.bases.services.BaseService;
import com.projeto.cicles.mappers.RespostaMapper;
import com.projeto.cicles.models.Resposta;
import com.projeto.cicles.repositories.RespostaRepository;
import com.projeto.cicles.requests.RespostaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RespostaService extends BaseService<Resposta, RespostaDTO> {

    public final RespostaMapper respostaMapper;

    @Autowired
    private RespostaRepository respostaRepository;


    public RespostaService(RespostaMapper respostaMapper) {
        this.respostaMapper = respostaMapper;
    }

    @Override
    public Resposta cadastrar(RespostaDTO objetoDTO) {

        Resposta resposta = respostaMapper.toEntity(objetoDTO);

        return respostaRepository.save(resposta);
    }

    @Override
    public Resposta editar(RespostaDTO objetoDTO, UUID id) {
        Resposta resposta = respostaMapper.toEntity(objetoDTO);
        resposta.setId(id);

        return respostaRepository.save(resposta);
    }

    @Override
    public void deletar(UUID id) {
        respostaRepository.deleteById(id);
    }

    @Override
    public Resposta findById(UUID id) {
        return respostaRepository.findById(id).orElseThrow(() -> new ValidacoesException("Resposta não encontrado com o ID: " + id));
    }

}

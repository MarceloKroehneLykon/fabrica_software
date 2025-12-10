package com.projeto.cicles.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.bases.services.BaseService;
import com.projeto.cicles.mappers.CicloMapper;
import com.projeto.cicles.models.Ciclo;
import com.projeto.cicles.repositories.CicloRepository;
import com.projeto.cicles.requests.CicloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CicloService extends BaseService<Ciclo, CicloDTO> {

    public final CicloMapper cicloMapper;

    @Autowired
    private CicloRepository cicloRepository;


    public CicloService(CicloMapper cicloMapper) {
        this.cicloMapper = cicloMapper;
    }

    @Override
    public Ciclo cadastrar(CicloDTO objetoDTO) {

        Ciclo ciclo = cicloMapper.toEntity(objetoDTO);

        return cicloRepository.save(ciclo);
    }

    @Override
    public Ciclo editar(CicloDTO objetoDTO, UUID id) {
        Ciclo ciclo = cicloMapper.toEntity(objetoDTO);
        ciclo.setId(id);

        return cicloRepository.save(ciclo);
    }

    @Override
    public void deletar(UUID id) {
        cicloRepository.deleteById(id);
    }

    @Override
    public Ciclo findById(UUID id) {
        return cicloRepository.findById(id).orElseThrow(() -> new ValidacoesException("Ciclo não encontrado com o ID: " + id));
    }

}

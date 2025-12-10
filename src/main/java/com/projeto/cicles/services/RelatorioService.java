package com.projeto.cicles.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.bases.services.CompleteService;
import com.projeto.cicles.mappers.RelatorioMapper;
import com.projeto.cicles.models.Relatorio;
import com.projeto.cicles.repositories.RelatorioRepository;
import com.projeto.cicles.requests.RelatorioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RelatorioService extends CompleteService<Relatorio, RelatorioDTO> {

    public final RelatorioMapper relatorioMapper;

    @Autowired
    private RelatorioRepository relatorioRepository;


    public RelatorioService(RelatorioMapper relatorioMapper) {
        this.relatorioMapper = relatorioMapper;
    }

    @Override
    public Relatorio cadastrar(RelatorioDTO objetoDTO) {

        Relatorio relatorio = relatorioMapper.toEntity(objetoDTO);

        return relatorioRepository.save(relatorio);
    }

    @Override
    public Relatorio editar(RelatorioDTO objetoDTO, UUID id) {
        Relatorio relatorio = relatorioMapper.toEntity(objetoDTO);
        relatorio.setId(id);

        return relatorioRepository.save(relatorio);
    }

    @Override
    public void deletar(UUID id) {
        relatorioRepository.deleteById(id);
    }

    @Override
    public Relatorio findById(UUID id) {
        return relatorioRepository.findById(id).orElseThrow(() -> new ValidacoesException("Relatorio não encontrado com o ID: " + id));
    }

    @Override
    public List<Relatorio> findAllByEmpresaId(Boolean ativo, int page, int size) {
        return relatorioRepository.findAllByEmpresaId(getEmpresaId());
    }
}

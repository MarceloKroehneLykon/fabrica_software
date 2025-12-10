package com.projeto.entities.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.bases.services.CompleteService;
import com.projeto.entities.mappers.SetorMapper;
import com.projeto.entities.models.Setor;
import com.projeto.entities.repositories.SetorRepository;
import com.projeto.entities.requests.SetorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SetorService extends CompleteService<Setor, SetorDTO> {

    public final SetorMapper setorMapper;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private EmpresaService empresaService;

    public SetorService(SetorMapper setorMapper) {
        this.setorMapper = setorMapper;
    }

    @Override
    public Setor cadastrar(SetorDTO objetoDTO) {

        Setor setor = setorMapper.toEntity(objetoDTO);
        setor.setEmpresa(empresaService.findById(objetoDTO.empresaId()));

        return setorRepository.save(setor);
    }

    @Override
    public Setor editar(SetorDTO objetoDTO, UUID id) {
        Setor setor = setorMapper.toEntity(objetoDTO);
        setor.setId(id);
        setor.setEmpresa(empresaService.findById(objetoDTO.empresaId()));

        return setorRepository.save(setor);
    }

    @Override
    public void deletar(UUID id) {
        setorRepository.deleteById(id);
    }

    @Override
    public Setor findById(UUID id) {
        return setorRepository.findById(id).orElseThrow(() -> new ValidacoesException("Setor não encontrado com o ID: " + id));
    }

    public List<Setor> findAllByEmpresaId(Boolean ativo, int page, int size) {
        return setorRepository.findAllByEmpresaId(getEmpresaId(), ativo);
    }
}

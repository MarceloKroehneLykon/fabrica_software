package com.projeto.bases.services;

import com.projeto.entities.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public abstract class BaseService<T,DTO> {

    public abstract T cadastrar(DTO objetoDTO);

    public abstract T editar(DTO objetoDTO, UUID id);

    public abstract void deletar(UUID id);

    public abstract T findById(UUID id);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private final Map<String, UUID> empresaIdCache = new ConcurrentHashMap<>();
    private final Map<String, UUID> funcionarioIdCache = new ConcurrentHashMap<>();

    public UUID getEmpresaId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        String email = auth.getName();

        return empresaIdCache.computeIfAbsent(email, e ->
                funcionarioRepository.findByEmail(e).getEmpresa().getId()
        );
    }

    public UUID getFuncionarioId(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;

        String email = auth.getName();
        return funcionarioIdCache.computeIfAbsent(email, e ->
                funcionarioRepository.findByEmail(e).getId()
        );
    }



}

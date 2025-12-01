package com.projeto.entities.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.entities.excepitons.CadastroEmpresaException;
import com.projeto.entities.mappers.EmpresaMapper;
import com.projeto.entities.models.Empresa;
import com.projeto.entities.models.Funcionario;
import com.projeto.entities.repositories.EmpresaRepository;
import com.projeto.entities.repositories.FuncionarioRepository;
import com.projeto.entities.requests.EmpresaDTO;
import com.projeto.library.Biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmpresaService {

    public final EmpresaMapper empresaMapper;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    public EmpresaService(EmpresaMapper empresaMapper) {
        this.empresaMapper = empresaMapper;
    }

    public Empresa cadastrar(EmpresaDTO empresaDTO){

        validarDadosSimples(empresaDTO);
        
        Empresa empresa = empresaMapper.toEntity(empresaDTO);

        return empresaRepository.save(empresa);
    }

    public Empresa editar(EmpresaDTO empresaDTO) {
        validarDadosEmpresa(empresaDTO, getEmpresaId());

        Empresa empresaStatus = findByLogado();

        Empresa empresa = empresaMapper.toEntity(empresaDTO);
        empresa.setId(getEmpresaId());
        return empresaRepository.save(empresa);
    }

    public void editarEmpresaSemLogar(Empresa empresa) {
        empresaRepository.save(empresa);
    }


    public UUID getEmpresaId(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        return funcionarioRepository.findByEmail(auth.getName()).getEmpresa().getId();
    }

    public Funcionario getFuncionario(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        return funcionarioRepository.findByEmail(auth.getName());
    }


    public Empresa findById(UUID id) {
        return empresaRepository.findById(id).orElseThrow(() -> new ValidacoesException("Empresa não encontrada com o ID: " + id));
    }

    private void validarDadosEmpresa(EmpresaDTO empresaDTO, UUID id) {

        validarDadosSimples(empresaDTO);

        if(!Biblioteca.isCpfCnpjValido(empresaDTO.cpfCnpj()))
            throw new ValidacoesException("O CPF ou CNPJ está inválido");


        if(validarCpfCnpjEmpresa(empresaDTO.cpfCnpj(), id))
            throw new CadastroEmpresaException("Já existe uma empresa cadastrada com esse CPF ou CNPJ", false);
    }

    private boolean validarCpfCnpjEmpresa(String cpf, UUID id) {
        Empresa empresa  = empresaRepository.findByCpf(cpf);
        if(empresa == null)
            return false;
        return !empresa.getId().equals(id);
    }

    public void validarDadosSimples(EmpresaDTO empresaDTO){
        if(!Biblioteca.StringMaiorIgual(empresaDTO.nome(), 3))
            throw new CadastroEmpresaException("O nome/razão social");
    }

    public Empresa findByLogado() {
        return findById(getEmpresaId());
    }

    public UUID findFuncionarioId(String email) {
        return empresaRepository.findFuncionarioId(email);
    }
}

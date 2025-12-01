package com.projeto.entities.services;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.bases.services.CompleteService;
import com.projeto.entities.enums.Permissoes;
import com.projeto.entities.mappers.FuncionarioMapper;
import com.projeto.entities.models.Funcionario;
import com.projeto.entities.repositories.FuncionarioRepository;
import com.projeto.entities.requests.FuncionarioDTO;
import com.projeto.library.Biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService extends CompleteService<Funcionario, FuncionarioDTO> implements UserDetailsService {

    public final FuncionarioMapper funcionarioMapper;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @Autowired
    public FuncionarioService(FuncionarioMapper funcionarioMapper) {
        this.funcionarioMapper = funcionarioMapper;
    }

    public Funcionario cadastrar(FuncionarioDTO funcionarioDTO){

        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));
        funcionario.setEmpresa(empresaService.findById(getEmpresaId()));
        funcionario.setCargo(funcionarioDTO.permissaoId());
        Biblioteca.validarDadosUsuario(funcionario);

        existeEmailCadastrado(funcionario.getEmail(), null);

        return funcionarioRepository.save(funcionario);
    }

    private void existeEmailCadastrado(String email, UUID id) {
        Funcionario funcionario = funcionarioRepository.findByEmail(email);
        if(funcionario != null && funcionario.getId() != null && !funcionario.getId().equals(id))
            throw new ValidacoesException("Já existe um usuário cadastrado com este email");
    }


    @Override
    public Funcionario editar(FuncionarioDTO funcionarioDTO, UUID id) {

        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario.setCargo(funcionarioDTO.permissaoId());
        funcionario.setId(id);
        funcionario.setEmpresa(empresaService.findById(getEmpresaId()));

        Funcionario fun = findById(id);

        if(fun.getCargo().getPermissao() == Permissoes.ADMIN)
            throw new ValidacoesException("Não é possível modificar o cargo do admin!");

        if(Biblioteca.isStringVazia(funcionario.getSenha()))
            funcionario.setSenha(fun.getSenha());

        Biblioteca.validarDadosUsuario(funcionario);

        existeEmailCadastrado(funcionario.getEmail(), id);

        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void deletar(UUID id) {
        
        Funcionario funcionario = findById(id);
        
        if(funcionario.getCargo().getPermissao() == Permissoes.ADMIN)
            throw new ValidacoesException("Não é possível excluir o usuário admin!");
        
        funcionarioRepository.deleteById(id);
    }

    @Override
    public Funcionario findById(UUID id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ValidacoesException("Funcionário não encontrado com o ID: " + id));
    }

    @Override
    public List<Funcionario> findAllByEmpresaId(Boolean ativo, int page, int size) {
        return funcionarioRepository.findAllByEmpresaId(getEmpresaId(), ativo);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = funcionarioRepository.findByEmail(email);

        if(userDetails == null || Biblioteca.isStringVazia(userDetails.getUsername()))
            throw new ValidacoesException("Funcionário não encontrado!");

        return userDetails;
    }

    public UUID findEmpresaByEmail(String username) {
        return funcionarioRepository.findByEmail(username).getEmpresa().getId();
    }

    public Funcionario cadastrarPadrao(Funcionario funcionario) {
        funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));

        Biblioteca.validarDadosSimples(funcionario);

        existeEmailCadastrado(funcionario.getEmail(), null);

        return funcionarioRepository.save(funcionario);
    }

    public void alterarSenha(String email, String senha) {
        Funcionario funcionario = funcionarioRepository.findByEmail(email);

        if(funcionario == null) {
            throw new ValidacoesException("Funcionário não encontrado com o email: " + email);
        }

        funcionario.setSenha(passwordEncoder.encode(senha));
        funcionarioRepository.save(funcionario);
    }

    public Boolean existsByEmail(String email) {
        return funcionarioRepository.existsByEmail(email);
    }

    public Funcionario findByLogado() {
        return findById(getFuncionarioId());
    }
}

package com.projeto.entities.services;

import com.projeto.entities.models.Empresa;
import com.projeto.entities.models.Funcionario;
import com.projeto.entities.requests.EmpresaDTO;
import com.projeto.entities.requests.FuncionarioDTO;
import com.projeto.library.Biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
public class CadastroEmpresaService {

    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private FuncionarioService funcionarioService;


    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static final int TAMANHO_PADRAO = 8;

    public static String gerarSenhaInicial() {
        StringBuilder senha = new StringBuilder(TAMANHO_PADRAO);
        for (int i = 0; i < TAMANHO_PADRAO; i++) {
            senha.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return senha.toString();
    }

    @Transactional
    public void cadastrarEmpresaUsuarioPadrao(EmpresaDTO empresaDTO, FuncionarioDTO funcionarioDTO){
        Empresa empresa = empresaService.cadastrar(empresaDTO);

        Funcionario funcionario = funcionarioService.funcionarioMapper.toEntity(funcionarioDTO);
        funcionario.setCargo(funcionarioDTO.permissaoId());
        funcionario.setEmpresa(empresa);
        funcionario.setAtivo(true);

        String senha = gerarSenhaInicial();
        if(Biblioteca.isStringVazia(funcionario.getSenha()))
            funcionario.setSenha(senha);

        funcionarioService.cadastrarPadrao(funcionario);

    }
}


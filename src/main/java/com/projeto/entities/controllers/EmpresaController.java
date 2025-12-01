package com.projeto.entities.controllers;

import com.projeto.entities.models.Empresa;
import com.projeto.entities.requests.CadastroDTO;
import com.projeto.entities.requests.EmpresaDTO;
import com.projeto.entities.services.CadastroEmpresaService;
import com.projeto.entities.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private CadastroEmpresaService cadastroEmpresaService;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public void cadastrar(@RequestBody CadastroDTO cadastroDTO){

        cadastroEmpresaService.cadastrarEmpresaUsuarioPadrao(cadastroDTO.empresaDTO(), cadastroDTO.funcionarioDTO());

    }

    @GetMapping("/find_funcionario_id")
    public ResponseEntity<UUID> findFuncionarioId(@Param("email") String email) {
        UUID retorno = empresaService.findFuncionarioId(email);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping
    public ResponseEntity<EmpresaDTO> editar(@RequestBody EmpresaDTO empresaDTO){
        Empresa empresa = empresaService.editar(empresaDTO);

        return ResponseEntity.ok(empresaService.empresaMapper.toDTO(empresa));
    }

    @GetMapping("/logado")
    public ResponseEntity<EmpresaDTO> findByLogado() {
        Empresa empresa = empresaService.findByLogado();
        return ResponseEntity.ok(empresaService.empresaMapper.toDTO(empresa));
    }
}

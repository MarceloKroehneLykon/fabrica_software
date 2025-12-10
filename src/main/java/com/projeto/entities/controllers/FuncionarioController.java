package com.projeto.entities.controllers;


import com.projeto.bases.controllers.CompleteController;
import com.projeto.entities.models.Funcionario;
import com.projeto.entities.requests.FuncionarioDTO;
import com.projeto.entities.responses.FuncionarioSaidaDTO;
import com.projeto.entities.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController implements CompleteController<FuncionarioDTO, FuncionarioSaidaDTO> {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioSaidaDTO> cadastrar(@RequestBody FuncionarioDTO objectDTO) {
        Funcionario funcionario = funcionarioService.cadastrar(objectDTO);
        return ResponseEntity.ok(funcionarioService.funcionarioMapper.toDTO(funcionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioSaidaDTO> editar(@RequestBody FuncionarioDTO objectDTO, @PathVariable UUID id) {
        Funcionario funcionario = funcionarioService.editar(objectDTO, id);
        return ResponseEntity.ok(funcionarioService.funcionarioMapper.toDTO(funcionario));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) {
        funcionarioService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

    @GetMapping("/find_by_id")
    public ResponseEntity<FuncionarioSaidaDTO> findById(@RequestParam UUID id) {
        Funcionario funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok(funcionarioService.funcionarioMapper.toDTO(funcionario));
    }

    @GetMapping("/find_all_by_empresa")
    public ResponseEntity<List<FuncionarioSaidaDTO>> findAllByEmpresa(@RequestParam(name = "ativo", required = false) Boolean ativo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Funcionario> funcionarios = funcionarioService.findAllByEmpresaId(ativo, page, size);
        return ResponseEntity.ok(funcionarioService.funcionarioMapper.toListDTO(funcionarios));
    }

    @GetMapping("/logado")
    public ResponseEntity<FuncionarioSaidaDTO> findByLogado() {
        Funcionario funcionario = funcionarioService.findByLogado();
        return ResponseEntity.ok(funcionarioService.funcionarioMapper.toDTO(funcionario));
    }

}

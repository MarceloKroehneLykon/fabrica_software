package com.projeto.entities.controllers;

import com.projeto.bases.controllers.CompleteController;
import com.projeto.entities.models.Setor;
import com.projeto.entities.requests.SetorDTO;
import com.projeto.entities.services.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setor")
public class SetorController implements CompleteController<SetorDTO, SetorDTO> {
    @Autowired
    private SetorService setorService;

    @Override
    public ResponseEntity<SetorDTO> cadastrar(SetorDTO objectDTO) {
        Setor setor = setorService.cadastrar(objectDTO);
        return ResponseEntity.ok(setorService.setorMapper.toDTO(setor));
    }

    @Override
    public ResponseEntity<SetorDTO> editar(SetorDTO objectDTO, UUID id) {
        Setor setor = setorService.editar(objectDTO, id);
        return ResponseEntity.ok(setorService.setorMapper.toDTO(setor));
    }

    @Override
    public ResponseEntity<String> deletar(UUID id) {
        setorService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }


    @Override
    public ResponseEntity<SetorDTO> findById(UUID id) {
        Setor setor = setorService.findById(id);
        return ResponseEntity.ok(setorService.setorMapper.toDTO(setor));
    }


    @Override
    public ResponseEntity<List<SetorDTO>> findAllByEmpresa(@RequestParam(name = "ativo", required = false) Boolean ativo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Setor> setores = setorService.findAllByEmpresaId(ativo, page, size);
        return ResponseEntity.ok(setorService.setorMapper.toListDTO(setores));
    }
}

package com.projeto.cicles.controllers;

import com.projeto.bases.controllers.BaseController;
import com.projeto.cicles.models.Ciclo;
import com.projeto.cicles.requests.CicloDTO;
import com.projeto.cicles.services.CicloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/ciclo")
public class CicloController implements BaseController<CicloDTO, CicloDTO> {

    @Autowired
    private CicloService cicloService;
    @Override
    public ResponseEntity<CicloDTO> cadastrar(CicloDTO objectDTO) {
        Ciclo ciclo = cicloService.cadastrar(objectDTO);
        return ResponseEntity.ok(cicloService.cicloMapper.toDTO(ciclo));
    }

    @Override
    public ResponseEntity<CicloDTO> editar(CicloDTO objectDTO, UUID id) {
        Ciclo ciclo = cicloService.editar(objectDTO,id);
        return ResponseEntity.ok(cicloService.cicloMapper.toDTO(ciclo));
    }

    @Override
    public ResponseEntity<String> deletar(UUID id) {
        cicloService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

    @Override
    public ResponseEntity<CicloDTO> findById(UUID id) {
        Ciclo ciclo = cicloService.findById(id);
        return ResponseEntity.ok(cicloService.cicloMapper.toDTO(ciclo));
    }

}

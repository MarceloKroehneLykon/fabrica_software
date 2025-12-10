package com.projeto.cicles.controllers;

import com.projeto.bases.controllers.CompleteController;
import com.projeto.cicles.models.Relatorio;
import com.projeto.cicles.requests.RelatorioDTO;
import com.projeto.cicles.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController implements CompleteController<RelatorioDTO, RelatorioDTO> {

    @Autowired
    private RelatorioService relatorioService;
    @Override
    public ResponseEntity<RelatorioDTO> cadastrar(RelatorioDTO objectDTO) {
        Relatorio relatorio = relatorioService.cadastrar(objectDTO);
        return ResponseEntity.ok(relatorioService.relatorioMapper.toDTO(relatorio));
    }

    @Override
    public ResponseEntity<RelatorioDTO> editar(RelatorioDTO objectDTO, UUID id) {
        Relatorio relatorio = relatorioService.editar(objectDTO,id);
        return ResponseEntity.ok(relatorioService.relatorioMapper.toDTO(relatorio));
    }

    @Override
    public ResponseEntity<String> deletar(UUID id) {
        relatorioService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

    @Override
    public ResponseEntity<RelatorioDTO> findById(UUID id) {
        Relatorio relatorio = relatorioService.findById(id);
        return ResponseEntity.ok(relatorioService.relatorioMapper.toDTO(relatorio));
    }

    @Override
    public ResponseEntity<List<RelatorioDTO>> findAllByEmpresa(Boolean ativo, int page, int size) {
        List<Relatorio> relatorios = relatorioService.findAllByEmpresaId(ativo,page,size);
        return ResponseEntity.ok(relatorioService.relatorioMapper.toListDTO(relatorios));
    }
}

package com.projeto.cicles.controllers;

import com.projeto.bases.controllers.BaseController;
import com.projeto.cicles.models.Pergunta;
import com.projeto.cicles.requests.PerguntaDTO;
import com.projeto.cicles.services.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController implements BaseController<PerguntaDTO, PerguntaDTO> {

    @Autowired
    private PerguntaService perguntaService;
    @Override
    public ResponseEntity<PerguntaDTO> cadastrar(PerguntaDTO objectDTO) {
        Pergunta pergunta = perguntaService.cadastrar(objectDTO);
        return ResponseEntity.ok(perguntaService.perguntaMapper.toDTO(pergunta));
    }

    @Override
    public ResponseEntity<PerguntaDTO> editar(PerguntaDTO objectDTO, UUID id) {
        Pergunta pergunta = perguntaService.editar(objectDTO,id);
        return ResponseEntity.ok(perguntaService.perguntaMapper.toDTO(pergunta));
    }

    @Override
    public ResponseEntity<String> deletar(UUID id) {
        perguntaService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

    @Override
    public ResponseEntity<PerguntaDTO> findById(UUID id) {
        Pergunta pergunta = perguntaService.findById(id);
        return ResponseEntity.ok(perguntaService.perguntaMapper.toDTO(pergunta));
    }
    
}

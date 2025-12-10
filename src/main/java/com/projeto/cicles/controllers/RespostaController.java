package com.projeto.cicles.controllers;

import com.projeto.bases.controllers.BaseController;
import com.projeto.cicles.models.Resposta;
import com.projeto.cicles.requests.RespostaDTO;
import com.projeto.cicles.services.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/resposta")
public class RespostaController implements BaseController<RespostaDTO, RespostaDTO> {

    @Autowired
    private RespostaService respostaService;
    @Override
    public ResponseEntity<RespostaDTO> cadastrar(RespostaDTO objectDTO) {
        Resposta resposta = respostaService.cadastrar(objectDTO);
        return ResponseEntity.ok(respostaService.respostaMapper.toDTO(resposta));
    }

    @Override
    public ResponseEntity<RespostaDTO> editar(RespostaDTO objectDTO, UUID id) {
        Resposta resposta = respostaService.editar(objectDTO,id);
        return ResponseEntity.ok(respostaService.respostaMapper.toDTO(resposta));
    }

    @Override
    public ResponseEntity<String> deletar(UUID id) {
        respostaService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

    @Override
    public ResponseEntity<RespostaDTO> findById(UUID id) {
        Resposta resposta = respostaService.findById(id);
        return ResponseEntity.ok(respostaService.respostaMapper.toDTO(resposta));
    }
    
}

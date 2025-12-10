package com.projeto.cicles.controllers;

import com.projeto.bases.controllers.BaseController;
import com.projeto.cicles.models.Questionario;
import com.projeto.cicles.requests.QuestionarioDTO;
import com.projeto.cicles.services.QuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/questionario")
public class QuestionarioController implements BaseController<QuestionarioDTO, QuestionarioDTO> {

    @Autowired
    private QuestionarioService questionarioService;
    @Override
    public ResponseEntity<QuestionarioDTO> cadastrar(QuestionarioDTO objectDTO) {
        Questionario questionario = questionarioService.cadastrar(objectDTO);
        return ResponseEntity.ok(questionarioService.questionarioMapper.toDTO(questionario));
    }

    @Override
    public ResponseEntity<QuestionarioDTO> editar(QuestionarioDTO objectDTO, UUID id) {
        Questionario questionario = questionarioService.editar(objectDTO,id);
        return ResponseEntity.ok(questionarioService.questionarioMapper.toDTO(questionario));
    }

    @Override
    public ResponseEntity<String> deletar(UUID id) {
        questionarioService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

    @Override
    public ResponseEntity<QuestionarioDTO> findById(UUID id) {
        Questionario questionario = questionarioService.findById(id);
        return ResponseEntity.ok(questionarioService.questionarioMapper.toDTO(questionario));
    }
    
}

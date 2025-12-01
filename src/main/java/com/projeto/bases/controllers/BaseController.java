package com.projeto.bases.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public interface BaseController <EDTO, SDTO>{

    ResponseEntity<SDTO> cadastrar(@RequestBody EDTO objectDTO);
    ResponseEntity<SDTO> editar(@RequestBody EDTO objectDTO, @PathVariable UUID id);
    ResponseEntity<String> deletar(@PathVariable UUID id);
    ResponseEntity<SDTO> findById(@RequestParam UUID id);

}

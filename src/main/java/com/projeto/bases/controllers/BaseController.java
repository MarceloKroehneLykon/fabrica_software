package com.projeto.bases.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public interface BaseController <EDTO, SDTO>{

    @PostMapping
    ResponseEntity<SDTO> cadastrar(@RequestBody EDTO objectDTO);
    @PutMapping("/{id}")
    ResponseEntity<SDTO> editar(@RequestBody EDTO objectDTO, @PathVariable UUID id);
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletar(@PathVariable UUID id);
    @GetMapping("/find_by_id")
    ResponseEntity<SDTO> findById(@RequestParam UUID id);

}

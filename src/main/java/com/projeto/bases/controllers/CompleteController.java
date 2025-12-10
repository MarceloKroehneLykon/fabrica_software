package com.projeto.bases.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CompleteController<EDTO, SDTO> extends BaseController<EDTO,SDTO> {
    @GetMapping("/find_all_by_empresa")
    ResponseEntity<List<SDTO>> findAllByEmpresa(Boolean ativo, int page, int size);

}

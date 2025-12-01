package com.projeto.bases.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompleteController<EDTO, SDTO> extends BaseController<EDTO,SDTO> {
    ResponseEntity<List<SDTO>> findAllByEmpresa(Boolean ativo, int page, int size);

}

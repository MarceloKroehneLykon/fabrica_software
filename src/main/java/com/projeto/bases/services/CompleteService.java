package com.projeto.bases.services;

import java.util.List;

public abstract class CompleteService<T,DTO> extends BaseService<T,DTO>{

    public abstract List<T> findAllByEmpresaId(Boolean ativo, int page, int size);

}

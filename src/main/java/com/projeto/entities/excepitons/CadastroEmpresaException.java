package com.projeto.entities.excepitons;

public class CadastroEmpresaException extends RuntimeException {

    public CadastroEmpresaException(String message){
        throw new CadastroEmpresaException(message, true);
    }
    public CadastroEmpresaException(String message, boolean mensagemPadrao) {
        super(message + (mensagemPadrao ? " informado para a empresa, é inválido!": ""));
    }
}

package com.consulta.Consulta_cep_api.Exception;

public class CepNaoEncontradoException extends RuntimeException{

    public CepNaoEncontradoException() {
        super();
    }

    public CepNaoEncontradoException(String message) {
        super(message);
    }
    public CepNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CepNaoEncontradoException(Throwable cause) {
        super(cause);
    }

}

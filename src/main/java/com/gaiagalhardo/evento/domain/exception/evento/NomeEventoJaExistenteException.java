package com.gaiagalhardo.evento.domain.exception.evento;

import com.gaiagalhardo.evento.domain.exception.NegocioException;

import java.io.Serial;

public class NomeEventoJaExistenteException extends NegocioException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NomeEventoJaExistenteException(String mensagem) {
        super(mensagem);
    }

}

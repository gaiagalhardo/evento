package com.gaiagalhardo.evento.domain.exception.evento;

import com.gaiagalhardo.evento.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventoNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EventoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EventoNaoEncontradoException(Long eventoId) {
        this(String.format("Não existe um evento cadastrado com o código %d", eventoId));
    }


}

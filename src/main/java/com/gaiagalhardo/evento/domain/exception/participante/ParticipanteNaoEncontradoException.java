package com.gaiagalhardo.evento.domain.exception.participante;

import com.gaiagalhardo.evento.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParticipanteNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ParticipanteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ParticipanteNaoEncontradoException(Long participanteId) {
        this(String.format("Não existe um participante com o ID %d", participanteId));
    }
}

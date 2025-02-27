package com.gaiagalhardo.evento.domain.exception.participante;

import com.gaiagalhardo.evento.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
public class NomeParticipanteJaExisteException extends NegocioException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NomeParticipanteJaExisteException(String mensagem) {
        super(mensagem);
    }
}

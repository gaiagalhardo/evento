package com.gaiagalhardo.evento.domain.model;

import lombok.Getter;

@Getter
public enum StatusEvento {
    PLANEJADO("O evento foi criado, mas ainda não começou."),
    EM_ANDAMENTO("O evento está em progresso."),
    FINALIZADO("O evento foi encerrado."),
    CANCELADO("O evento foi cancelado.");

    private final String descricao;

    private StatusEvento(String descricao) {
        this.descricao = descricao;
    }

}
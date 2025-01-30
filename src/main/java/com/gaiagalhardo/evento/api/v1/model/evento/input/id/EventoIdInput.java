package com.gaiagalhardo.evento.api.v1.model.evento.input.id;

// Representação do ID para cadastro em outras classes

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoIdInput {

    @NotNull
    private Long id;

}
package com.gaiagalhardo.evento.api.v1.model.evento.input;

// Representação dos dados do evento que serão registrados pelo usuário

import com.gaiagalhardo.evento.domain.model.StatusEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class EventoInput {

    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    private OffsetDateTime dataInicio;

    @NotNull
    private OffsetDateTime dataFinal;

    @NotBlank
    private String localizacao;

    @NotNull
    private StatusEvento status;

    private String fotoCapa;
    private String url;
    private String organizador;
    private String contatoOrganizador;

    @NotNull
    private Integer capacidade;
}

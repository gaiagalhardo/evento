package com.gaiagalhardo.evento.api.v1.model.evento;

// Representação detalhada dos dados de um evento

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "eventos")
@Getter
@Setter
public class EventoDetails extends RepresentationModel<EventoDetails> {

    // Futuramente informar nessa classe a documentação via swagger

    private Long id;
    private String nome;
    private String descricao;
    private String dataCriacao;
    private String dataInicio;
    private String dataFinal;
    private String status;
    private String localizacao;
    private String fotoCapa;
    private String url;
    private String organizador;
    private String contatoOrganizador;
    private Integer capacidade;

}

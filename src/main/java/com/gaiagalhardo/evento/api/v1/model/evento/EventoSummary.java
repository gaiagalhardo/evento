package com.gaiagalhardo.evento.api.v1.model.evento;

// Representação resumida dos dados de um evento

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "eventos")
@Getter
@Setter
public class EventoSummary extends RepresentationModel<EventoSummary> {

    // Futuramente informar nessa classe a documentação via swagger

    private Long id;
    private String nome;
    private String dataInicio;
    private String dataFinal;
    private Integer capacidade;

}

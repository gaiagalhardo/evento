package com.gaiagalhardo.evento.api.v1.assembler.evento;

import com.gaiagalhardo.evento.api.v1.controller.EventoController;
import com.gaiagalhardo.evento.api.v1.model.evento.EventoSummary;
import com.gaiagalhardo.evento.domain.model.Evento;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

// Transformando objetos Evento para EventoSummary
// Realizar o mapeamento entre os dois tipos de objetos para exibir na resposta quando necessário

@Component
public class EventoSummaryAssembler extends RepresentationModelAssemblerSupport<Evento, EventoSummary> {

    private final ModelMapper modelMapper;

    public EventoSummaryAssembler(ModelMapper modelMapper) {
        super(EventoController.class, EventoSummary.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public EventoSummary toModel(Evento evento) {
        EventoSummary eventoSummary = createModelWithId(evento.getId(), evento);

        modelMapper.map(evento, eventoSummary);

        return eventoSummary;
    }

    @Override
    public CollectionModel<EventoSummary> toCollectionModel(Iterable<? extends  Evento> entities) {

        return super.toCollectionModel(entities);
    }
}

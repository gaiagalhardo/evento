package com.gaiagalhardo.evento.api.v1.assembler.evento;

import com.gaiagalhardo.evento.api.v1.controller.EventoController;
import com.gaiagalhardo.evento.api.v1.model.evento.EventoDetails;
import com.gaiagalhardo.evento.domain.model.Evento;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


// Transformando objetos Evento para EventoSummary
// Realizar o mapeamento entre os dois tipos de objetos para exibir na resposta quando necessário

@Component
public class EventoDetailsAssembler extends RepresentationModelAssemblerSupport<Evento, EventoDetails> {

    private final ModelMapper modelMapper;

    public EventoDetailsAssembler() {
        super(EventoController.class, EventoDetails.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public EventoDetails toModel(Evento evento) {

        EventoDetails eventoDetails = createModelWithId(evento.getId(), evento);
        modelMapper.map(evento, eventoDetails);

        return eventoDetails;
    }

    @Override
    public CollectionModel<EventoDetails> toCollectionModel(Iterable<? extends Evento> entities) {
        return super.toCollectionModel(entities);
    }
}

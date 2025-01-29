package com.gaiagalhardo.evento.api.v1.assembler.evento;

import com.gaiagalhardo.evento.api.v1.controller.EventoController;
import com.gaiagalhardo.evento.api.v1.model.evento.EventoMinimal;
import com.gaiagalhardo.evento.domain.model.Evento;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

// Transformando objetos Evento para EventoMinimal
// Realizar o mapeamento entre os dois tipos de objetos para exibir na resposta quando necessário

@Component
public class EventoMinimalAssembler extends RepresentationModelAssemblerSupport<Evento, EventoMinimal> {

    private final ModelMapper modelMapper;

    public EventoMinimalAssembler() {
        super(EventoController.class, EventoMinimal.class);
        this.modelMapper = new ModelMapper();
    }

    @Override
    public EventoMinimal toModel(Evento evento) {

        EventoMinimal eventoMinimal = createModelWithId(evento.getId(), evento);
        modelMapper.map(evento, eventoMinimal);

        return eventoMinimal;
    }

    public CollectionModel<EventoMinimal> toCollectionModel(Iterable<? extends Evento> entities) {
        return super.toCollectionModel(entities);
    }
}

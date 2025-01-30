package com.gaiagalhardo.evento.api.v1.assembler.evento;

import com.gaiagalhardo.evento.api.v1.model.evento.input.EventoInput;
import com.gaiagalhardo.evento.domain.model.Evento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// Transforma os objetos do tipo EventoInput em objetos da entidade Evento

@Component
public class EventoInputDisassembler {

    private final ModelMapper modelMapper;

    public EventoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Evento toDomainObject(EventoInput eventoInput){
        return modelMapper.map(eventoInput, Evento.class);
    }

    public void copyToDomainObject(EventoInput eventoInput, Evento evento){
        modelMapper.map(eventoInput, evento);
    }

}

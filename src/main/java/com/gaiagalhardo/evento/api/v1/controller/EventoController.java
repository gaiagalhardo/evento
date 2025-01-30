package com.gaiagalhardo.evento.api.v1.controller;

import com.gaiagalhardo.evento.api.v1.assembler.evento.EventoDetailsAssembler;
import com.gaiagalhardo.evento.api.v1.assembler.evento.EventoInputDisassembler;
import com.gaiagalhardo.evento.api.v1.assembler.evento.EventoSummaryAssembler;
import com.gaiagalhardo.evento.api.v1.model.evento.EventoDetails;
import com.gaiagalhardo.evento.api.v1.model.evento.EventoSummary;
import com.gaiagalhardo.evento.api.v1.model.evento.input.EventoInput;
import com.gaiagalhardo.evento.domain.exception.EntidadeNaoEncontradaException;
import com.gaiagalhardo.evento.domain.exception.NegocioException;
import com.gaiagalhardo.evento.domain.model.Evento;
import com.gaiagalhardo.evento.domain.repository.EventoRepository;
import com.gaiagalhardo.evento.domain.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/eventos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventoController {

    private final EventoService eventoService;
    private final EventoRepository eventoRepository;
    private final EventoSummaryAssembler eventoSummaryAssembler;
    private final EventoDetailsAssembler eventoDetailsAssembler;
    private final EventoInputDisassembler eventoInputDisassembler;
    private final PagedResourcesAssembler<Evento> pagedResourcesAssembler;


    public EventoController(EventoService  eventoService,
                            EventoRepository eventoRepository,
                            EventoSummaryAssembler eventoSummaryAssembler,
                            EventoDetailsAssembler eventoDetailsAssembler,
                            EventoInputDisassembler eventoInputDisassembler,
                            PagedResourcesAssembler<Evento> pagedResourcesAssembler) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
        this.eventoSummaryAssembler = eventoSummaryAssembler;
        this.eventoDetailsAssembler = eventoDetailsAssembler;
        this.eventoInputDisassembler = eventoInputDisassembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    // Lista todos os eventos cadastrados na forma resumida
    @GetMapping
    public CollectionModel<EventoSummary> listar(){
        return eventoSummaryAssembler.toCollectionModel(eventoRepository.findAll());
    }

    // Lista em formato de paginação
    @GetMapping("page")
    public PagedModel<EventoSummary> paginate(Pageable pageable){
        Page<Evento> eventoPage = eventoRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(eventoPage, eventoSummaryAssembler);
    }

    // Busca um evento via id e retorna uma exibição detalhada
    @GetMapping("/{id}")
    public EventoDetails buscar(@PathVariable Long id){
        return eventoDetailsAssembler.toModel(eventoService.buscarOuFalhar(id));
    }

    // Adicionar/Salva um registro de evento
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventoDetails adicionar(@RequestBody @Valid EventoInput eventoInput) {
        Evento eventos = eventoInputDisassembler.toDomainObject(eventoInput);
        eventos = eventoService.salvar(eventos);

        return eventoDetailsAssembler.toModel(eventos);
    }

    // Atualiza um registro de evento
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private EventoDetails atualizar(@PathVariable Long id, @RequestBody @Valid EventoInput eventoInput) {
        try{
            Evento eventoAtual  = eventoService.buscarOuFalhar(id);
            eventoInputDisassembler.copyToDomainObject(eventoInput, eventoAtual);
            return eventoDetailsAssembler.toModel(eventoService.salvar(eventoAtual));
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        eventoService.excluir(id);
    }
}

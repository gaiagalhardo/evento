package com.gaiagalhardo.evento.domain.service;

import com.gaiagalhardo.evento.domain.exception.EntidadeEmUsoException;
import com.gaiagalhardo.evento.domain.exception.evento.EventoNaoEncontradoException;
import com.gaiagalhardo.evento.domain.exception.evento.NomeEventoJaExistenteException;
import com.gaiagalhardo.evento.domain.model.Evento;
import com.gaiagalhardo.evento.domain.repository.EventoRepository;

import jakarta.persistence.EntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventoService {

    //Mensagem de Evento em Uso
    private static final String MSG_EVENTO_EM_USO = "Evento de %d não pode ser removido. O Evento está em uso.";

    private final EventoRepository eventoRepository;
    private final EntityManager manager;

    public EventoService(EventoRepository eventoRepository, EntityManager manager) {
        this.eventoRepository = eventoRepository;
        this.manager = manager;
    }

    // Salvar o evento
    @Transactional
    public Evento salvar(Evento evento) {

        manager.detach(evento);

        Optional<Evento> nomeEventoExistente = eventoRepository.findByNomeIgnoreCase(evento.getNome());

        // Verifica se já existe um evento
        if (nomeEventoExistente.isPresent() && !nomeEventoExistente.get().equals(evento)) {
            throw new NomeEventoJaExistenteException("Evento já cadastrado no sistema.");
        }

        return eventoRepository.save(evento);
    }

    // Excluir o evento
    @Transactional
    public void excluir(Long id) {
        try{
            eventoRepository.deleteById(id);
            eventoRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new EventoNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_EVENTO_EM_USO, id));
        }
    }

    //Realiza uma busca para verificar se já existe um cadastro com o id informado.
    @Transactional
    public Evento buscarOuFalhar(Long id){
        return eventoRepository.findById(id).orElseThrow(() -> new EventoNaoEncontradoException(id));
    }

}

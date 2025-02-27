package com.gaiagalhardo.evento.domain.service;

import com.gaiagalhardo.evento.domain.exception.EntidadeEmUsoException;
import com.gaiagalhardo.evento.domain.exception.participante.NomeParticipanteJaExisteException;
import com.gaiagalhardo.evento.domain.exception.participante.ParticipanteNaoEncontradoException;
import com.gaiagalhardo.evento.domain.model.Participante;
import com.gaiagalhardo.evento.domain.repository.ParticipanteRepository;
import jakarta.persistence.EntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ParticipanteService {

    //Mensagem de Participante em Uso
    private static final String MSG_PARTICIPANTE_EM_USO = "Participante de %d não pode ser removido. O Participante está em uso.";

    private final ParticipanteRepository participanteRepository;
    private final EntityManager manager;

    public ParticipanteService(ParticipanteRepository participanteRepository, EntityManager manager) {
        this.participanteRepository = participanteRepository;
        this.manager = manager;
    }

    // Salvar o participante
    @Transactional
    public Participante salvar(Participante participante) {

        manager.detach(participante);

        Optional<Participante> nomeParticipanteExistente = participanteRepository.findByNomeIgnoreCase(participante.getNome());

        // Verifica se já existe um participante
        if (nomeParticipanteExistente.isPresent() && !nomeParticipanteExistente.get().equals(participante)) {
            throw new NomeParticipanteJaExisteException("Participante já cadastrado no sistema.");
        }

        return participanteRepository.save(participante);
    }

    // Excluir o participante
    @Transactional
    public void excluir(Long id) {
        try{
            participanteRepository.deleteById(id);
            participanteRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new ParticipanteNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_PARTICIPANTE_EM_USO, id));
        }
    }

    //Realiza uma busca para verificar se já existe um cadastro com o id informado.
    @Transactional
    public Participante buscarOuFalhar(Long id){
        return participanteRepository.findById(id).orElseThrow(() -> new ParticipanteNaoEncontradoException(id));
    }

}

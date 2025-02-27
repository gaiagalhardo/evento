package com.gaiagalhardo.evento.domain.repository;

import com.gaiagalhardo.evento.domain.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    public Optional<Participante> findByNomeIgnoreCase(String nome);

}

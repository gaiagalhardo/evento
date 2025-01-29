package com.gaiagalhardo.evento.domain.repository;

import com.gaiagalhardo.evento.domain.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    public Optional<Evento> findByNomeIgnoreCase(String nome);
}

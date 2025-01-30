package com.gaiagalhardo.evento.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Evento {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;

    private String descricao;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_inicio")
    private OffsetDateTime dataInicio;

    @Column(name = "data_final")
    private OffsetDateTime dataFinal;

    private String localizacao;

    @Enumerated(EnumType.STRING)
    private StatusEvento status;

    @Column(name = "foto_capa")
    private String fotoCapa;

    private String url;
    private String organizador;

    @Column(name = "contato_organizador")
    private String contatoOrganizador;

    private Integer capacidade;

}

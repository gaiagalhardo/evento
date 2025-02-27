package com.gaiagalhardo.evento.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class Participante {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "qr_code")
    private String qrCode;

    private String nome;
    private String email;
    private String cpf;
    private String celular;

    @PrePersist
    public void qrCodeGenerated() {
        setQrCode(UUID.randomUUID().toString());
    }

}

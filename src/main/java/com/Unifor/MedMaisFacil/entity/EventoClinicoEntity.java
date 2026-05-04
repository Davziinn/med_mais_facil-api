package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "TB_EVNT_CLNC")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EventoClinicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVNT")
    private Long id;

    @Column(name = "DESC_ENVT", nullable = false, unique = true)
    private String descricao;
}

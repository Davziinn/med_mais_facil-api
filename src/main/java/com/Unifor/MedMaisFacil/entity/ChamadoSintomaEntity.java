package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CHAM_SINT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChamadoSintomaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CHAM_SINT")
    private Long id;

    @Column(name = "INT_SINT", nullable = false)
    private Integer intecidade;

    @Column(name = "DESC_SINT")
    private String descricao;

    @Column(name = "DT_REG_SINT")
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "chamado_id")
    private ChamadoEntity chamado;

    @ManyToOne
    @JoinColumn(name = "sintoma_id")
    private SintomaEntity sintoma;
}

package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ATEND")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class AtendimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATEND")
    private Long id;

    @Column(name = "OBS_ATEND")
    private String observacoes;

    @CreationTimestamp
    @Column(name = "DT_INI", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "DT_FIM")
    private LocalDateTime dataFim;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id", nullable = false, unique = true)
    private ChamadoEntity chamado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false, unique = true)
    private MedicoEntity medico;
}

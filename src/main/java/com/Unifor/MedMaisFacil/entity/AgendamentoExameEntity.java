package com.Unifor.MedMaisFacil.entity;

import com.Unifor.MedMaisFacil.enums.StatusAgendamentoExame;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_AGND_EXAM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AgendamentoExameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGND_EXAM")
    private Long id;

    @Column(name = "DT_HR_AGEND", nullable = false)
    private LocalDateTime dataHoraAgendamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_AGND_EXAM", nullable = false)
    private StatusAgendamentoExame status;

    @Column(name = "OBS_AGEND")
    private String observacoes;

    @CreationTimestamp
    @Column(name = "DT_CRI_AGEND", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "DT_ATZ_AGEND")
    private LocalDateTime atualizadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guia_exame_id", nullable = false)
    private GuiaExameEntity guiaExame;
}

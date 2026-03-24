package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*;

@Entity
@Table(name = "TB_PRNT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProntuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRNT")
    private Long id;

    @Column(name = "DIAG_PRNT", nullable = false)
    private String diagnostico;

    @Column(name = "OBS_PRNT")
    private String observacoes;

    @Column(name = "PRSC_PRNT", nullable = false)
    private String prescricao;

    @CreationTimestamp
    @Column(name = "DT_REGS_PRNT", updatable = false)
    private LocalDateTime dataRegistro;

    @UpdateTimestamp
    @Column(name = "DT_ATZ_PRNT")
    private LocalDateTime atualizadoEm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento_id", nullable = false, unique = true)
    private AtendimentoEntity atendimento;
}

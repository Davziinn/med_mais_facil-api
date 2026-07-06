package com.Unifor.MedMaisFacil.entity;

import com.Unifor.MedMaisFacil.enums.Convenio;
import com.Unifor.MedMaisFacil.enums.StatusGuiaMedica;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_GUIA_MED")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GuiaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GUIA_MED")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_GUIA_MED", nullable = false)
    private StatusGuiaMedica statusGuiaMedica;

    @Column(name = "NMR_GUIA",nullable = false,unique = true)
    private String numeroGuia;

    @CreationTimestamp
    @Column(name = "DT_SOLIC_GUIA_MED", nullable = false, updatable = false)
    private LocalDateTime dataSolicitacao;

    @Column(name = "guiaMedica_convenio",nullable = false)
    @Enumerated(EnumType.STRING)
    private Convenio convenio;

    @Column(name = "CID_GUIA_MED", nullable = false)
    private String cidExame;

    @Column(name = "IND_CLNC", nullable = false)
    private String indicacaoClinica;

    @Column(name = "OBS_GUIA_MED", length = 300)
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento_id", nullable = false)
    private AtendimentoEntity atendimento;

    @OneToMany(mappedBy = "guia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GuiaExameEntity> exames = new ArrayList<>();
}

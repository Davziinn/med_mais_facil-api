package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_PRSC_MEDC")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PrescricaoMedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NM_MEDC")
    private String nome;
    @Column(name = "DS_MEDC")
    private String dose;
    @Column(name = "FREQ_MEDC")
    private String frequencia;
    @Column(name = "DUR_MEDC")
    private String duracao;
    @Column(name = "VIA_MEDC")
    private String via;

    @ManyToOne
    @JoinColumn(name = "prescricao_id")
    private PrescricaoEntity prescricao;
}

package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_PRSC")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PrescricaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRSC")
    private Long id;

    @Column(name = "ORI_PRSC")
    private String orientacoes;
    @Column(name = "RTN_CONS")
    private String retornoConsulta;
    @Column(name = "EXAMES")
    private String exames;

    @OneToOne
    @JoinColumn(name = "atendimento_id", unique = true)
    private AtendimentoEntity atendimento;

    @OneToMany(mappedBy = "prescricao", cascade = CascadeType.ALL)
    private List<PrescricaoMedicamentoEntity> medicamentos;
}

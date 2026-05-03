package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Prescricao {

    private Long id;
    private String orientacoes;
    private String retornoConsulta;
    private String exames;
    private Atendimento atendimento;
    private List<PrescricaoMedicamento> medicamentos;
}

package com.Unifor.MedMaisFacil.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PrescricaoMedicamento {

    private Long id;
    private String nome;
    private String dose;
    private String frequencia;
    private String duracao;
    private String via;
//    private Prescricao prescricao;
}

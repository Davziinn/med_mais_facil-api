package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Prontuario {

    private Long id;
    private String diagnostico;
    private String observacoes;
    private String prescricao;
    private LocalDateTime dataRegistro;
    private LocalDateTime atualizadoEm;
    private Atendimento atendimento;
}

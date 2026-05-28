package com.Unifor.MedMaisFacil.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Configuracao {

    private Long id;
    private Integer tempoLimiteChamado;
    private Integer quantidadeMaximaFila;
    private Boolean chamadaAutomatica;
    private String statusGeral;
    private String mensagemPaciente;
    private Boolean notificacoesPush;
}

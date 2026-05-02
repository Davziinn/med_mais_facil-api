package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Atendimento {

    private Long id;
    private String anamnese;
    private String exameFisico;
    private String hipoteseDiagnostica;
    private String cidDoenca;
    private String conduta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Chamado chamado;
    private Medico medico;

    public void validarDataAtendimento(LocalDateTime novaData){
        if(this.dataFim.isBefore(novaData)){
            throw new IllegalStateException("Data final invalida");
        }
        if(this.dataInicio.isBefore(novaData)){
            throw new IllegalStateException("Data inicial invalida");
        }
    }
}

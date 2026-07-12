package com.Unifor.MedMaisFacil.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RecepcaoDashboardMetricas {
    private long aguardandoCheckin;
    private long aguardando;
    private long ausentes;
    private long aguardandoEncaminhamento;
    private LocalDateTime tempoMedioEspera;
}

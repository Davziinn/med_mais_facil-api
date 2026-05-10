package com.Unifor.MedMaisFacil.dtos.atendimento.historicoMetricas;

public record HistoricoMetricasResponseDTO(
        long totalPeriodo,
        long finalizados,
        long cancelados,
        double taxaCancelamento
) {
}

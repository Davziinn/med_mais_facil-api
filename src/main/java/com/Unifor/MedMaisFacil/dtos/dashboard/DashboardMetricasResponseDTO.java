package com.Unifor.MedMaisFacil.dtos.dashboard;

public record DashboardMetricasResponseDTO(
        long chamadosHoje,
        long aguardando,
        long emAtendimento,
        long finalizados
) {
}

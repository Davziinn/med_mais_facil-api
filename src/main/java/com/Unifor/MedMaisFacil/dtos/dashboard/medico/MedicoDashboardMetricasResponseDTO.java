package com.Unifor.MedMaisFacil.dtos.dashboard.medico;

public record MedicoDashboardMetricasResponseDTO(
        long chamadosHoje,
        long aguardando,
        long emAtendimento,
        long finalizados
) {
}

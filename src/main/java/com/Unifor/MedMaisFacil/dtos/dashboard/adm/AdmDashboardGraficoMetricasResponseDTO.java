package com.Unifor.MedMaisFacil.dtos.dashboard.adm;

public record AdmDashboardGraficoMetricasResponseDTO(
        long quantidadeChamadosCritica,
        long quantidadeChamadosAlta,
        long quantidadeChamadosMedia,
        long quantidadeChamadosBaixa
) {
}

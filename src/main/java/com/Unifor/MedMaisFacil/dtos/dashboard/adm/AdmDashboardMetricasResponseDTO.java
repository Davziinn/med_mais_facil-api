package com.Unifor.MedMaisFacil.dtos.dashboard.adm;

import java.util.List;

public record AdmDashboardMetricasResponseDTO(
    long quantidadePacientes,
    long quantidadeMedicos,
    long quantidadeRecepcionistas,
    long quantidadeAdms,
    long quantidadeHospitais,
    long chamadosAtivosHoje,
    long chamadosFinalizadosHoje,
    long chamadosCanceladosHoje,
    long quantidadePacientesAusentesHoje,
    long chamadosEmAtendimento,
    long chamadosEmEspera,
    AdmDashboardGraficoMetricasResponseDTO graficoMetricas,
    List<AdmDashboardAtendimentosPorDiaResponseDTO> atendimentosPorDia
) {
}

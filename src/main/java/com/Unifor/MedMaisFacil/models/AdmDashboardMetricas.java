package com.Unifor.MedMaisFacil.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdmDashboardMetricas {

    private long quantidadePacientes;
    private long quantidadeMedicos;
    private long quantidadeRecepcionistas;
    private long quantidadeAdms;
    private long quantidadeHospitais;
    private long chamadosAtivosHoje;
    private long chamadosFinalizadosHoje;
    private long chamadosCanceladosHoje;
    private long quantidadePacientesAusentesHoje;
    private long chamadosEmAtendimento;
    private long chamadosEmEspera;
    private AdmDashboardGraficoMetricas graficoMetricas;
    private List<AdmDashboardAtendimentosPorDia> atendimentosPorDia;
}

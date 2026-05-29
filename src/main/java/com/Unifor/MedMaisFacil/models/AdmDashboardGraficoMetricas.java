package com.Unifor.MedMaisFacil.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdmDashboardGraficoMetricas {

    private long quantidadeChamadosCritica;
    private long quantidadeChamadosAlta;
    private long quantidadeChamadosMedia;
    private long quantidadeChamadosBaixa;
}
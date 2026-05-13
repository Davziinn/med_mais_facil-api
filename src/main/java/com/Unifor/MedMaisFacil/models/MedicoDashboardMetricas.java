package com.Unifor.MedMaisFacil.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MedicoDashboardMetricas {

    private long chamadosHoje;
    private long aguardando;
    private long emAtendimento;
    private long finalizados;
}

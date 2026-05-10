package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.atendimento.historicoMetricas.HistoricoMetricasResponseDTO;
import com.Unifor.MedMaisFacil.models.HistoricoMetricas;

public interface HistoricoMetricasMapper {
    HistoricoMetricas toModel (HistoricoMetricasResponseDTO dto);

    HistoricoMetricasResponseDTO toDTO (HistoricoMetricas model);

}

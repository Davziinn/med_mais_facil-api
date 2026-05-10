package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.atendimento.historicoMetricas.HistoricoMetricasResponseDTO;
import com.Unifor.MedMaisFacil.models.HistoricoMetricas;
import org.springframework.stereotype.Component;

@Component
public class HistoricoMetricasMapperImpl implements HistoricoMetricasMapper{

    @Override
    public HistoricoMetricas toModel(HistoricoMetricasResponseDTO dto) {
        return new HistoricoMetricas(
                dto.totalPeriodo(),
                dto.finalizados(),
                dto.cancelados(),
                dto.taxaCancelamento()
        );
    }

    @Override
    public HistoricoMetricasResponseDTO toDTO(HistoricoMetricas model) {
        return new HistoricoMetricasResponseDTO(
                model.getTotalPeriodo(),
                model.getFinalizados(),
                model.getCancelados(),
                model.getTaxaCancelamento()
        );
    }
}

package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.dashboard.DashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.models.DashboardMetricas;
import org.springframework.stereotype.Component;

@Component
public class DashboardMetricasMapperImpl implements DashboardMetricasMapper{

    @Override
    public DashboardMetricas toModel(DashboardMetricasResponseDTO dto) {
        return new DashboardMetricas(
                dto.chamadosHoje(),
                dto.aguardando(),
                dto.emAtendimento(),
                dto.finalizados()
        );
    }

    @Override
    public DashboardMetricasResponseDTO toDTO(DashboardMetricas model) {
        return new DashboardMetricasResponseDTO(
                model.getChamadosHoje(),
                model.getAguardando(),
                model.getEmAtendimento(),
                model.getFinalizados()
        );
    }
}

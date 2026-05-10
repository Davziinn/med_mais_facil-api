package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.dashboard.DashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.models.DashboardMetricas;

public interface DashboardMetricasMapper {

    DashboardMetricas toModel (DashboardMetricasResponseDTO dto);

    DashboardMetricasResponseDTO toDTO (DashboardMetricas model);
}

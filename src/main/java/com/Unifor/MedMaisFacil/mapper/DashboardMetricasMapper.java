package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.dashboard.adm.AdmDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.medico.MedicoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.recepcao.RecepcaoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.models.AdmDashboardMetricas;
import com.Unifor.MedMaisFacil.models.MedicoDashboardMetricas;
import com.Unifor.MedMaisFacil.models.RecepcaoDashboardMetricas;

public interface DashboardMetricasMapper {

    MedicoDashboardMetricas toModelMedico(MedicoDashboardMetricasResponseDTO dto);

    MedicoDashboardMetricasResponseDTO toDTOMedico(MedicoDashboardMetricas model);

    RecepcaoDashboardMetricasResponseDTO toDTORecepcao(RecepcaoDashboardMetricas model);

    AdmDashboardMetricasResponseDTO toDTOAdm (AdmDashboardMetricas model);
}

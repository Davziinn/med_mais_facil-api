package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.dashboard.medico.MedicoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.recepcao.RecepcaoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.models.MedicoDashboardMetricas;
import com.Unifor.MedMaisFacil.models.RecepcaoDashboardMetricas;
import org.springframework.stereotype.Component;

@Component
public class DashboardMetricasMapperImpl implements DashboardMetricasMapper{

    @Override
    public MedicoDashboardMetricas toModelMedico(MedicoDashboardMetricasResponseDTO dto) {
        return new MedicoDashboardMetricas(
                dto.chamadosHoje(),
                dto.aguardando(),
                dto.emAtendimento(),
                dto.finalizados()
        );
    }

    @Override
    public MedicoDashboardMetricasResponseDTO toDTOMedico(MedicoDashboardMetricas model) {
        return new MedicoDashboardMetricasResponseDTO(
                model.getChamadosHoje(),
                model.getAguardando(),
                model.getEmAtendimento(),
                model.getFinalizados()
        );
    }

    @Override
    public RecepcaoDashboardMetricasResponseDTO toDTORecepcao(RecepcaoDashboardMetricas model) {
        return new RecepcaoDashboardMetricasResponseDTO(
                model.getAguardandoCheckin(),
                model.getAguardando(),
                model.getAusentes(),
                model.getTempoMedioEspera()
        );
    }
}

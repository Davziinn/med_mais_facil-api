package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.dashboard.adm.AdmDashboardAtendimentosPorDiaResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.adm.AdmDashboardGraficoMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.adm.AdmDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.medico.MedicoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.recepcao.RecepcaoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.models.AdmDashboardMetricas;
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

    @Override
    public AdmDashboardMetricasResponseDTO toDTOAdm(AdmDashboardMetricas model) {
        return new AdmDashboardMetricasResponseDTO(
                model.getQuantidadePacientes(),
                model.getQuantidadeMedicos(),
                model.getQuantidadeRecepcionistas(),
                model.getQuantidadeAdms(),
                model.getQuantidadeHospitais(),
                model.getChamadosAtivosHoje(),
                model.getChamadosFinalizadosHoje(),
                model.getChamadosCanceladosHoje(),
                model.getQuantidadePacientesAusentesHoje(),
                model.getChamadosEmAtendimento(),
                model.getChamadosEmEspera(),
                model.getGraficoMetricas() != null ? new AdmDashboardGraficoMetricasResponseDTO(
                        model.getGraficoMetricas().getQuantidadeChamadosCritica(),
                        model.getGraficoMetricas().getQuantidadeChamadosAlta(),
                        model.getGraficoMetricas().getQuantidadeChamadosMedia(),
                        model.getGraficoMetricas().getQuantidadeChamadosBaixa()
                ) : null,
                model.getAtendimentosPorDia() != null
                        ? model.getAtendimentosPorDia().stream()
                        .map(a -> new AdmDashboardAtendimentosPorDiaResponseDTO(a.getDia(), a.getQuantidadeAtendimentos()))
                        .toList()
                        : null
        );
    }
}

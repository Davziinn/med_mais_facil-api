package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ChamadoService chamadoService;

    private final PacienteService pacienteService;

    private final MedicoService medicoService;

    private final UsuarioService usuarioService;

    private final HospitalService hospitalService;

    private final AtendimentoService atendimentoService;

    public MedicoDashboardMetricas buscarMetricasMedicoLogado () {
        long finalizados = chamadoService.contarQuantidadeChamadosPorEspecialidadeEPorStatusDoMedicoLogado(StatusChamado.FINALIZADO);
        long emAtendimento = chamadoService.contarQuantidadeChamadosPorEspecialidadeEPorStatusDoMedicoLogado(StatusChamado.EM_ATENDIMENTO);
        long aguardando = chamadoService.contarQuantidadeChamadosPorEspecialidadeEPorStatusDoMedicoLogado(StatusChamado.EM_ESPERA);

        long chamadosHoje = chamadoService.contarQuantidadeChamadosPorEspecialidadeDoMedicoLogado();

        return new MedicoDashboardMetricas(chamadosHoje, aguardando, emAtendimento, finalizados);
    }

    public RecepcaoDashboardMetricas buscarMetricasRecepcao () {
        long aguardandoCheckin = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.AGUARDANDO_CHECKIN);
        long aguardando = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ESPERA);
        long ausentes = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.AUSENTE);
        long aguardandoEncaminhamento = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.AGUARDANDO_ENCAMINHAMENTO);
        LocalDateTime tempoMedioEspera = LocalDateTime.now();

        return new RecepcaoDashboardMetricas(aguardandoCheckin, aguardando, ausentes, aguardandoEncaminhamento, tempoMedioEspera);
    }


    public List<Chamado> buscarFilaEmAtendimento () {
        return chamadoService.buscarChamadosEmAtendimentoDiaAtual();
    }

    public List<Chamado> buscarFilaAguardandoCheckin() {
        return chamadoService.buscarPacientesAguardandoCheckin();
    }

    public AdmDashboardMetricas buscarMetricasAdm() {
        long quantidadePacientes = pacienteService.contarQuantidadePacientes();
        long quantidadeMedicosAtivos = medicoService.contarQuantidadeMedicosCadastrados();
        long quantidadeRecepcicaoAtivos = usuarioService.contarQuantidadeUsuariosRecepcaoAtivos();
        long quantidadeAdmAtivos = usuarioService.contarQuantidadeUsuariosAdmAtivos();

        long quantidadeHospitais = hospitalService.contarQuantidadeHospitaisCadastrados();
        long quantidadeChamadosEmEsperaHoje = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ESPERA);
        long quantidadeChamadosEmAtendimentoHoje = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ATENDIMENTO);
        long quantidadeChamadosAtivosHoje = quantidadeChamadosEmEsperaHoje + quantidadeChamadosEmAtendimentoHoje;

        long quantidadeChamadosFinalizadosHoje = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.FINALIZADO);
        long quantidadeChamadosCanceladosHoje = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.CANCELADO);

        long quantidadePacientesAusentes = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.AUSENTE);

        AdmDashboardGraficoMetricas metricasGraficoBarraBuscado = buscarMetricasGrafico();

        List<AdmDashboardAtendimentosPorDia> metricasAtendimentosUltimos7DiasBuscado = buscarMetricaAtendimentosPorDia();

        return new AdmDashboardMetricas(
                quantidadePacientes,
                quantidadeMedicosAtivos,
                quantidadeRecepcicaoAtivos,
                quantidadeAdmAtivos,
                quantidadeHospitais,
                quantidadeChamadosAtivosHoje,
                quantidadeChamadosFinalizadosHoje,
                quantidadeChamadosCanceladosHoje,
                quantidadePacientesAusentes,
                quantidadeChamadosEmAtendimentoHoje,
                quantidadeChamadosEmEsperaHoje,
                metricasGraficoBarraBuscado,
                metricasAtendimentosUltimos7DiasBuscado
        );
    }



    public AdmDashboardGraficoMetricas buscarMetricasGrafico() {
        long quantidadeChamadosCritica = chamadoService.contarQuantidadeChamadosByPrioridadeChamado(PrioridadeChamado.CRITICA);
        long quantidadeChamadosAlta = chamadoService.contarQuantidadeChamadosByPrioridadeChamado(PrioridadeChamado.ALTA);
        long quantidadeChamadosMedia = chamadoService.contarQuantidadeChamadosByPrioridadeChamado(PrioridadeChamado.MEDIA);
        long quantidadeChamadosBaixa = chamadoService.contarQuantidadeChamadosByPrioridadeChamado(PrioridadeChamado.BAIXA);

        return new AdmDashboardGraficoMetricas(quantidadeChamadosCritica, quantidadeChamadosAlta, quantidadeChamadosMedia, quantidadeChamadosBaixa);
    }

    public List<AdmDashboardAtendimentosPorDia> buscarMetricaAtendimentosPorDia () {
        return atendimentoService.atendimentosUltimosSeteDias();
    }
}

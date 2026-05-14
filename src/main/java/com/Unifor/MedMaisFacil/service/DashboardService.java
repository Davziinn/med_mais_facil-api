package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.MedicoDashboardMetricas;
import com.Unifor.MedMaisFacil.models.RecepcaoDashboardMetricas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ChamadoService chamadoService;

    public MedicoDashboardMetricas buscarMetricasMedico () {
        long finalizados = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.FINALIZADO);
        long emAtendimento = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ATENDIMENTO);
        long aguardando = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ESPERA);

        long chamadosHoje = chamadoService.contarQuantidadeChamadosByDia();

        return new MedicoDashboardMetricas(chamadosHoje, aguardando, emAtendimento, finalizados);
    }

    public RecepcaoDashboardMetricas buscarMetricasRecepcao () {
        long aguardandoCheckin = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.AGUARDANDO_CHECKIN);
        long aguardando = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ESPERA);
        long ausentes = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.AUSENTE);
        LocalDateTime tempoMedioEspera = LocalDateTime.now();

        return new RecepcaoDashboardMetricas(aguardandoCheckin, aguardando, ausentes, tempoMedioEspera);
    }

    public List<Chamado> buscarFilaEmAtendimento () {
        return chamadoService.buscarChamadosEmAtendimentoDiaAtual();
    }

    public List<Chamado> buscarFilaAguardandoCheckin() {
        return chamadoService.buscarPacientesAguardandoCheckin();
    }
}

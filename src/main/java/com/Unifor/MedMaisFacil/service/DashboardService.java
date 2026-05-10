package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.DashboardMetricas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ChamadoService chamadoService;

    public DashboardMetricas buscarMetricas () {
        long finalizados = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.FINALIZADO);
        long emAtendimento = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ATENDIMENTO);
        long aguardando = chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.EM_ESPERA);

        long chamadosHoje = chamadoService.contarQuantidadeChamadosByDia();

        return new DashboardMetricas(chamadosHoje, aguardando, emAtendimento, finalizados);
    }

    public List<Chamado> buscarFilaEmAtendimento () {
        return chamadoService.buscarChamadosEmAtendimentoDiaAtual();
    }
}

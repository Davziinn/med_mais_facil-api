package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.dashboard.DashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.fila.filaEmAtendimento.FilaEmAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.mapper.DashboardMetricasMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.DashboardMetricas;
import com.Unifor.MedMaisFacil.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final DashboardMetricasMapper dashboardMetricasMapper;

    private final ChamadoMapper chamadoMapper;

    @GetMapping("/metricas")
    public ResponseEntity<DashboardMetricasResponseDTO> consultarMetricas () {
        DashboardMetricas consulta = dashboardService.buscarMetricas();

        return ResponseEntity.ok(dashboardMetricasMapper.toDTO(consulta));
    }

    @GetMapping
    public ResponseEntity<List<FilaEmAtendimentoResponseDTO>> listarFilaEmAtendimento () {
        List<Chamado> filaEmAtendimentoModel = dashboardService.buscarFilaEmAtendimento();

        return ResponseEntity.ok(filaEmAtendimentoModel.stream().map(chamadoMapper::toFilaAtendimentoDTO).toList());
    }

}

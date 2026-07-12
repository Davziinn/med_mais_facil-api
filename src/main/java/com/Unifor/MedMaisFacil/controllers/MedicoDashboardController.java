package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.dashboard.medico.MedicoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.fila.filaEmAtendimento.FilaEmAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.mapper.DashboardMetricasMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.MedicoDashboardMetricas;
import com.Unifor.MedMaisFacil.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO')")
@RestController
@RequestMapping("/v1/medico")
@RequiredArgsConstructor
public class MedicoDashboardController {

    private final DashboardService dashboardService;
    private final DashboardMetricasMapper dashboardMetricasMapper;

    private final ChamadoMapper chamadoMapper;

    @GetMapping("/metricas")
    public ResponseEntity<MedicoDashboardMetricasResponseDTO> consultarMetricas () {
        MedicoDashboardMetricas consulta = dashboardService.buscarMetricasMedicoLogado();

        return ResponseEntity.ok(dashboardMetricasMapper.toDTOMedico(consulta));
    }

    @GetMapping
    public ResponseEntity<List<FilaEmAtendimentoResponseDTO>> listarFilaEmAtendimento () {
        List<Chamado> filaEmAtendimentoModel = dashboardService.buscarFilaEmAtendimento();

        return ResponseEntity.ok(filaEmAtendimentoModel.stream().map(chamadoMapper::toFilaAtendimentoDTO).toList());
    }

}

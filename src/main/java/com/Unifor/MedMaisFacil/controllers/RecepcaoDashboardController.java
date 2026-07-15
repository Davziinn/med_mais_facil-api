package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.dashboard.recepcao.FilaAguardandoCheckinResponseDTO;
import com.Unifor.MedMaisFacil.dtos.dashboard.recepcao.RecepcaoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.mapper.DashboardMetricasMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.RecepcaoDashboardMetricas;
import com.Unifor.MedMaisFacil.service.ChamadoService;
import com.Unifor.MedMaisFacil.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'RECEPCAO')")
@RestController
@RequestMapping("/v1/recepcao")
@RequiredArgsConstructor
public class RecepcaoDashboardController {

    private final DashboardService dashboardService;
    private final DashboardMetricasMapper dashboardMetricasMapper;
    private final ChamadoMapper chamadoMapper;
    private final ChamadoService chamadoService;

    @GetMapping("/metricas")
    public ResponseEntity<RecepcaoDashboardMetricasResponseDTO> consultarMetricas() {
        RecepcaoDashboardMetricas model = dashboardService.buscarMetricasRecepcao();

        return ResponseEntity.ok(dashboardMetricasMapper.toDTORecepcao(model));
    }

    @GetMapping
    public ResponseEntity<Page<FilaAguardandoCheckinResponseDTO>> consultarFilaCheckin (
            @RequestParam(defaultValue = "0") int numeroPagina,
            @RequestParam(defaultValue = "5") int quantidadeElementosPorPagina
    ) {
        Page<Chamado> filaConsultada = dashboardService.buscarFilaAguardandoCheckin(PageRequest.of(numeroPagina, quantidadeElementosPorPagina));

        return ResponseEntity.ok(filaConsultada
                .map(chamadoMapper::toFilaCheckinDTO));
    }

    @PatchMapping("/{chamadoId}/ausencia")
    public ResponseEntity<Void> marcarPacienteAusente (@PathVariable Long chamadoId) {
        chamadoService.marcarAusente(chamadoId);

        return ResponseEntity.noContent().build();
    }
}

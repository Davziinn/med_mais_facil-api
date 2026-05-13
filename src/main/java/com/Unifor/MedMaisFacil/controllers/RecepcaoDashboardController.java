package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.dashboard.recepcao.RecepcaoDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.mapper.DashboardMetricasMapper;
import com.Unifor.MedMaisFacil.models.RecepcaoDashboardMetricas;
import com.Unifor.MedMaisFacil.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/recepcao")
@RequiredArgsConstructor
public class RecepcaoDashboardController {

    private final DashboardService dashboardService;
    private final DashboardMetricasMapper dashboardMetricasMapper;

    @GetMapping("/metricas")
    public ResponseEntity<RecepcaoDashboardMetricasResponseDTO> consultarMetricas() {
        RecepcaoDashboardMetricas model = dashboardService.buscarMetricasRecepcao();

        return ResponseEntity.ok(dashboardMetricasMapper.toDTORecepcao(model));
    }
}

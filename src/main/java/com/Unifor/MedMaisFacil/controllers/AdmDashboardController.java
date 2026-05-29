package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.dashboard.adm.AdmDashboardMetricasResponseDTO;
import com.Unifor.MedMaisFacil.mapper.DashboardMetricasMapper;
import com.Unifor.MedMaisFacil.models.AdmDashboardMetricas;
import com.Unifor.MedMaisFacil.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/adm")
@RequiredArgsConstructor
public class AdmDashboardController {

    private final DashboardService dashboardService;
    private final DashboardMetricasMapper dashboardMetricasMapper;

    @GetMapping("/metricas")
    public ResponseEntity<AdmDashboardMetricasResponseDTO> consultarMetricas () {
        AdmDashboardMetricas metricasBuscadas = dashboardService.buscarMetricasAdm();

        return ResponseEntity.ok(dashboardMetricasMapper.toDTOAdm(metricasBuscadas));
    }
}

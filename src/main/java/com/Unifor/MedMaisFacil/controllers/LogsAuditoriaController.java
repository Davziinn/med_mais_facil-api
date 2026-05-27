package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.logsAuditoria.LogsAuditoriaResponseDTO;
import com.Unifor.MedMaisFacil.mapper.LogsAuditoriaMapper;
import com.Unifor.MedMaisFacil.models.LogsAuditoria;
import com.Unifor.MedMaisFacil.service.LogsAuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/logs")
@RequiredArgsConstructor
public class LogsAuditoriaController {

    private final LogsAuditoriaService logsAuditoriaService;

    @GetMapping
    public ResponseEntity<Page<LogsAuditoriaResponseDTO>> listarLogs (
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) String modulo,
            @PageableDefault(size = 20, sort = "criadoEm") Pageable page) {
        LogsAuditoria filtro = LogsAuditoria.builder()
                .usuarioId(usuarioId)
                .modulo(modulo)
                .build();

        return ResponseEntity.ok(logsAuditoriaService.listarLogs(filtro, page));
    }
}

package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaRequestDTO;
import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaResponseDTO;
import com.Unifor.MedMaisFacil.mapper.GuiaMedicaMapper;
import com.Unifor.MedMaisFacil.models.GuiaMedica;
import com.Unifor.MedMaisFacil.service.GuiaMedicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO')")
@RequestMapping("/v1/guia-exame")
@RequiredArgsConstructor
public class GuiaMedicaController {

    private final GuiaMedicaService guiaMedicaSerivce;
    private final GuiaMedicaMapper guiaMedicaMapper;

    @PostMapping
    public ResponseEntity<GuiaMedicaResponseDTO> gerarGuiaExame (@Valid @RequestBody GuiaMedicaRequestDTO dto) {
        GuiaMedica guiaGerada = guiaMedicaSerivce.salvarGuiaMedica(guiaMedicaMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(guiaMedicaMapper.toDTO(guiaGerada));
    }
}

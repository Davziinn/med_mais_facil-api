package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaCanceladaResponseDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO')")
@RequestMapping("/v1/guia-medica")
@RequiredArgsConstructor
public class GuiaMedicaController {

    private final GuiaMedicaService guiaMedicaSerivce;
    private final GuiaMedicaMapper guiaMedicaMapper;

    @PostMapping
    public ResponseEntity<GuiaMedicaResponseDTO> gerarGuiaExame (@Valid @RequestBody GuiaMedicaRequestDTO dto) {
        GuiaMedica guiaGerada = guiaMedicaSerivce.salvarGuiaMedica(guiaMedicaMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(guiaMedicaMapper.toDTO(guiaGerada));
    }

    @GetMapping("/{atendimentoId}")
    public ResponseEntity<List<GuiaMedicaResponseDTO>> listarTodosGuiasAtendimento (@PathVariable Long atendimentoId) {
        List<GuiaMedica> guiasMedicas = guiaMedicaSerivce.consultarGuiasAtendimento(atendimentoId);

        return ResponseEntity.ok(guiasMedicas.stream().map(guiaMedicaMapper::toDTO).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuiaMedicaResponseDTO> editarGuiaMedica (@PathVariable Long id, @RequestBody GuiaMedicaRequestDTO dto) {
        GuiaMedica guiaMedicaEditada = guiaMedicaSerivce.editarGuiaMedica(id, dto);
        return ResponseEntity.ok(guiaMedicaMapper.toDTO(guiaMedicaEditada));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GuiaMedicaCanceladaResponseDTO> cancelarGuiaMedica (@PathVariable Long id) {
        GuiaMedica guiaMedicaCancelada = guiaMedicaSerivce.cancelarGuiaMedica(id);

        return ResponseEntity.ok(guiaMedicaMapper.toDTOCancelada(guiaMedicaCancelada));
    }
}

package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.prescricao.PrescricaoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.prescricao.PrescricaoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.PrescricaoMapper;
import com.Unifor.MedMaisFacil.models.Prescricao;
import com.Unifor.MedMaisFacil.service.PrescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/prescrever")
public class PrescricaoController {

    @Autowired
    private PrescricaoService prescricaoService;

    @Autowired
    private PrescricaoMapper prescricaoMapper;

    @PostMapping("/{atendimentoId}")
    public ResponseEntity<PrescricaoResponseDTO> salvarPrescricaoMedica (@PathVariable Long atendimentoId, @RequestBody PrescricaoRequestDTO prescricaoRequest) {
        Prescricao prescricaoSalvo = prescricaoService.salvar(prescricaoMapper.toModel(prescricaoRequest), atendimentoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(prescricaoMapper.toDTO(prescricaoSalvo));
    }
}

package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.medico.*;
import com.Unifor.MedMaisFacil.mapper.MedicoMapper;
import com.Unifor.MedMaisFacil.models.Medico;
import com.Unifor.MedMaisFacil.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoMapper medicoMapper;

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrarMedico (@Valid @RequestBody MedicoRequestDTO medicoRequest) {
        Medico medicoSalvo = medicoService.salvarMedico(medicoMapper.toModel(medicoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(medicoMapper.toDTO(medicoSalvo));
    }
}

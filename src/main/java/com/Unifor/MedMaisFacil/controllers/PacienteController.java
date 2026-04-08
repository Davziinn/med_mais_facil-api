package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.paciente.PacienteRequestDTO;
import com.Unifor.MedMaisFacil.dtos.paciente.PacienteResponseDTO;
import com.Unifor.MedMaisFacil.mapper.PacienteMapper;
import com.Unifor.MedMaisFacil.models.Paciente;
import com.Unifor.MedMaisFacil.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteMapper pacienteMapper;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente (@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        Paciente pacienteCadastrado = pacienteService.salvarPaciente(pacienteMapper.toModel(pacienteRequestDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteMapper.toDTO(pacienteCadastrado));
    }
}

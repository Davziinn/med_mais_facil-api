package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.paciente.*;
import com.Unifor.MedMaisFacil.mapper.PacienteMapper;
import com.Unifor.MedMaisFacil.models.Paciente;
import com.Unifor.MedMaisFacil.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes () {
        List<Paciente> pacientesBuscado = pacienteService.buscarTodosPacientes();

        return ResponseEntity.status(HttpStatus.CREATED).body(pacientesBuscado.stream().map(pacienteMapper::toDTO).toList());
    }

    @GetMapping("/{nomePaciente}")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPacienteByNome (@PathVariable String nomePaciente) {
        List<Paciente> pacientesEncontrados = pacienteService.buscarPacienteByNome(nomePaciente);

        return ResponseEntity.ok(pacientesEncontrados.stream().map(pacienteMapper::toDTO).toList());
    }
}

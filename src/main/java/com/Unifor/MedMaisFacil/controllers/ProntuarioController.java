package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.prontuario.ProntuarioPacienteResponseDTO;
import com.Unifor.MedMaisFacil.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO')")
@RestController
@RequestMapping("/v1/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping("/{pacienteId}/paciente")
    public ResponseEntity<ProntuarioPacienteResponseDTO> consultarProntuarioPaciente (@PathVariable Long pacienteId) {
        return ResponseEntity.ok(prontuarioService.buscarProntuario(pacienteId));
    }
}

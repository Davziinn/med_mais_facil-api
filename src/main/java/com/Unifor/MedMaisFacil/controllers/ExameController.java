package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.exame.ExameRequestDTO;
import com.Unifor.MedMaisFacil.dtos.exame.ExameResponseDTO;
import com.Unifor.MedMaisFacil.mapper.ExameMapper;
import com.Unifor.MedMaisFacil.models.Exame;
import com.Unifor.MedMaisFacil.service.ExameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO')")
@RequestMapping("/v1/exame")
@RequiredArgsConstructor
public class ExameController {

    private final ExameService exameService;
    private final ExameMapper exameMapper;

    @PostMapping
    public ResponseEntity<ExameResponseDTO> cadastrarExame (@Valid @RequestBody ExameRequestDTO dto) {
        Exame exameSalvo = exameService.salvarExame(exameMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(exameMapper.toDTO(exameSalvo));
    }

    @GetMapping
    public ResponseEntity<List<ExameResponseDTO>> listarExames () {
        List<Exame> exameSalvo = exameService.buscarTodosExamesCadastrados();
        return ResponseEntity.ok(exameSalvo.stream().map(exameMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameResponseDTO> buscarDadosExame (@PathVariable Long id) {
        Exame exameSalvo = exameService.buscarExameById(id);
        return ResponseEntity.ok(exameMapper.toDTO(exameSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExameResponseDTO> editarDadosExame (@PathVariable Long id, @RequestBody ExameRequestDTO dto ) {
        Exame exameEditado = exameService.editarDadosExame(id, exameMapper.toModel(dto));
        return ResponseEntity.ok(exameMapper.toDTO(exameEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExame (@PathVariable Long id) {
        exameService.deletarExameById(id);
        return ResponseEntity.noContent().build();
    }
}

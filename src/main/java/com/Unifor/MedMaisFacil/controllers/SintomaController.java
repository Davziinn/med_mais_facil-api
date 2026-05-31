package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.sintoma.*;
import com.Unifor.MedMaisFacil.mapper.SintomaMapper;
import com.Unifor.MedMaisFacil.models.Sintoma;
import com.Unifor.MedMaisFacil.service.SintomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PACIENTE')")
@RestController
@RequestMapping("/v1/sintoma")
public class SintomaController {

    @Autowired
    private SintomaService sintomaService;

    @Autowired
    private SintomaMapper sintomaMapper;

    @PostMapping
    public ResponseEntity<SintomaResponseDTO> cadastrarSintoma (@RequestBody SintomaRequestDTO sintomaRequestDTO) {
        Sintoma sintomaCadastrado = sintomaService.salvarSintoma(sintomaMapper.toModel(sintomaRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(sintomaMapper.toDTO(sintomaCadastrado));
    }

    @GetMapping
    public ResponseEntity<List<SintomaResponseDTO>> consultarTodosSintomas () {
        List<Sintoma> sintomasListados = sintomaService.listarSintomasCadastrados();

        return ResponseEntity.ok(sintomasListados.stream().map(sintomaMapper::toDTO).toList());
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<SintomaResponseDTO> editarSintoma (@PathVariable Long id, @RequestBody SintomaRequestDTO dto) {
        Sintoma sintomaAtualizado = sintomaService.atualizarSintoma(id, sintomaMapper.toModel(dto));

        return ResponseEntity.ok(sintomaMapper.toDTO(sintomaAtualizado));
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletarSintoma (@PathVariable Long id) {
        sintomaService.deletarSintoma(id);

        return ResponseEntity.noContent().build();
    }
}

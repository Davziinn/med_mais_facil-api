package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.sintoma.*;
import com.Unifor.MedMaisFacil.mapper.SintomaMapper;
import com.Unifor.MedMaisFacil.models.Sintoma;
import com.Unifor.MedMaisFacil.service.SintomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
}

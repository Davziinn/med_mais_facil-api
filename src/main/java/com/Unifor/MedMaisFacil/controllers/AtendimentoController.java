package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.iniciarAtendimento.*;
import com.Unifor.MedMaisFacil.mapper.AtendimentoMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @PostMapping("/iniciar")
    public ResponseEntity<IniciarAtendimentoResponseDTO> iniciarAtendimento (@RequestBody IniciarAtendimentoRequestDTO iniciarAtendimentoRequestDTO) {
        Atendimento atendimentoIniciado = atendimentoService.iniciar(iniciarAtendimentoRequestDTO.chamadoId(), iniciarAtendimentoRequestDTO.medicoId());

        return ResponseEntity.ok(atendimentoMapper.toDTO(atendimentoIniciado));
    }
}

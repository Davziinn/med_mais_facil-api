package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.encerrarAtendimento.EncerrarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.iniciarAtendimento.*;
import com.Unifor.MedMaisFacil.dtos.salvarAtendimento.SalvarAtendimentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.salvarAtendimento.SalvarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.AtendimentoMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        return ResponseEntity.ok(atendimentoMapper.toIniciarDTO(atendimentoIniciado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalvarAtendimentoResponseDTO> atualizarDadosAtendimento (@PathVariable Long id, @RequestBody SalvarAtendimentoRequestDTO atualizarDadosAtendimento) {
        Atendimento atendimentoAtualizado = atendimentoService.salvar(id, atendimentoMapper.toModel(atualizarDadosAtendimento));

        return ResponseEntity.ok(atendimentoMapper.toSalvarDTO(atendimentoAtualizado));
    }

    @PutMapping("/encerrar/{id}")
    public ResponseEntity<EncerrarAtendimentoResponseDTO> encerrarAtendimento (@PathVariable Long id) {
        Atendimento atendimentoEncerrado = atendimentoService.encerrarAtendimento(id);

        return ResponseEntity.ok(atendimentoMapper.toEncerrarDTO(atendimentoEncerrado));
    }
}

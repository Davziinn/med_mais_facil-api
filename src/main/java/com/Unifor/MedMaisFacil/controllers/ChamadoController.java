package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.ChamadoAberto;
import com.Unifor.MedMaisFacil.models.Sintoma;
import com.Unifor.MedMaisFacil.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/chamado")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private ChamadoMapper chamadoMapper;

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> abrirChamado (@Valid @RequestBody ChamadoRequestDTO chamadoRequestDTO) {
        Chamado chamado = chamadoMapper.toModel(chamadoRequestDTO);
        List<Sintoma> sintomas = chamadoMapper.toSintomas(chamadoRequestDTO);

        ChamadoAberto chamadoAberto = chamadoService.abrirChamado(chamado, sintomas);

        ChamadoResponseDTO resposta = chamadoMapper.toDTO(chamadoAberto.chamado(), chamadoAberto.chamadoSintomas());

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
}

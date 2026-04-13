package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.chamadoSintoma.ChamadoSintomaResponseDTO;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.mapper.ChamadoSintomaMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.ChamadoAberto;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;
import com.Unifor.MedMaisFacil.models.SintomaDoChamado;
import com.Unifor.MedMaisFacil.service.ChamadoService;
import com.Unifor.MedMaisFacil.service.ChamadoSintomaService;
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

    @Autowired
    private ChamadoSintomaService chamadoSintomaService;

    @Autowired
    private ChamadoSintomaMapper chamadoSintomaMapper;

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> abrirChamado (@Valid @RequestBody ChamadoRequestDTO chamadoRequestDTO) {
        Chamado chamado = chamadoMapper.toModel(chamadoRequestDTO);
        List<SintomaDoChamado> sintomas = chamadoMapper.toSintomas(chamadoRequestDTO);

        ChamadoAberto chamadoAberto = chamadoService.abrirChamado(chamado, sintomas);

        ChamadoResponseDTO resposta = chamadoMapper.toDTO(chamadoAberto.chamado(), chamadoAberto.chamadoSintomas());

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoSintomaResponseDTO> buscarChamadoSintomaById (@PathVariable Long id) {
        ChamadoSintoma chamadoBuscado = chamadoSintomaService.buscarDetalhesSintomaById(id);

        ChamadoSintomaResponseDTO dto = chamadoSintomaMapper.toDTO(chamadoBuscado);

        return ResponseEntity.status(HttpStatus.FOUND).body(dto);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<ChamadoResponseDTO> consultarDetalhesChamado(@PathVariable Long id) {
        Chamado chamado = chamadoService.consultarDetalhesChamado(id);
        return ResponseEntity.ok(chamadoMapper.toDTO(chamado, chamado.getSintomas()));
    }
}

package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.chamado.*;
import com.Unifor.MedMaisFacil.dtos.chamadoSintoma.ChamadoSintomaResponseDTO;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
        return ResponseEntity.ok(chamadoMapper.toDTO(chamado, chamado.getChamadoSintomas()));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> consultarChamados() {
        List<Chamado> chamados = chamadoService.listarTodosChamadosAtivos();

        List<ChamadoResponseDTO> listaChamadodsDto = chamados.stream()
                .map(chamado -> chamadoMapper.toDTO(
                        chamado,
                        chamado.getChamadoSintomas() != null
                                ? chamado.getChamadoSintomas()
                                : List.of()
                ))
                .toList();

        return ResponseEntity.ok(listaChamadodsDto);
    }
}

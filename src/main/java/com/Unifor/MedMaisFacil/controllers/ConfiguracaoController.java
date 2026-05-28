package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.configuracao.ConfiguracaoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.configuracao.ConfiguracaoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.ConfiguracaoMapper;
import com.Unifor.MedMaisFacil.models.Configuracao;
import com.Unifor.MedMaisFacil.service.ConfiguracaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/configuracao")
@RequiredArgsConstructor
public class ConfiguracaoController {

    private final ConfiguracaoService configuracaoService;
    private final ConfiguracaoMapper configuracaoMapper;

    @GetMapping
    public ResponseEntity<ConfiguracaoResponseDTO> buscarDadosConfiguracao () {
        Configuracao configuracoesEncontradas = configuracaoService.buscar();
        return ResponseEntity.ok(configuracaoMapper.toDTO(configuracoesEncontradas));
    }

    @PutMapping
    public ResponseEntity<ConfiguracaoResponseDTO> salvarConfiguracoes (@RequestBody ConfiguracaoRequestDTO dto) {
        Configuracao configuracaoSalva = configuracaoService.salvar(configuracaoMapper.toModel(dto));
        return ResponseEntity.ok(configuracaoMapper.toDTO(configuracaoSalva));
    }
}

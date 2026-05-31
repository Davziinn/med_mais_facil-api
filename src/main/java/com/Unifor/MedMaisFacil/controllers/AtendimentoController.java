package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.atendimento.encerrarAtendimento.EncerrarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.historicoAtendimento.HistoricoAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.historicoMetricas.HistoricoMetricasResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.iniciarAtendimento.IniciarAtendimentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.iniciarAtendimento.IniciarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.salvarAtendimento.SalvarAtendimentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.salvarAtendimento.SalvarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.AtendimentoMapper;
import com.Unifor.MedMaisFacil.mapper.HistoricoMetricasMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.HistoricoMetricas;
import com.Unifor.MedMaisFacil.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO')")
@RestController
@RequestMapping("/v1/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private HistoricoMetricasMapper historicoMetricasMapper;

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

    @GetMapping("/historico")
    public ResponseEntity<List<HistoricoAtendimentoResponseDTO>> listarHistoricoAtendimentos () {
        List<Atendimento> historicos = atendimentoService.buscarHistoricoAtendimentos();

        List<HistoricoAtendimentoResponseDTO> dtos = atendimentoMapper.toHistoricoDTO(historicos);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/metricas")
    public ResponseEntity<HistoricoMetricasResponseDTO> consultarMetricasHistorico () {
        HistoricoMetricas historicoMetricasModel = atendimentoService.consultarHistoricoMetricasTrimestral();

        return ResponseEntity.ok(historicoMetricasMapper.toDTO(historicoMetricasModel));
    }
}

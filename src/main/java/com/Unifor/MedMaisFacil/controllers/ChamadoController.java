package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.chamado.*;
import com.Unifor.MedMaisFacil.dtos.chamadoEventoClinico.ChamadoEventoClinicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.chamadoSintoma.ChamadoSintomaResponseDTO;
import com.Unifor.MedMaisFacil.dtos.detalheChamado.DetalheChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.fila.filaEspera.FilaEsperaResponseDTO;
import com.Unifor.MedMaisFacil.dtos.prioridade.AlterarPrioridadeRequestDTO;
import com.Unifor.MedMaisFacil.dtos.prioridade.AlterarPrioridadeResponseDTO;
import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PACIENTE', 'RECEPCAO')")
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

    @Autowired
    private ChamadoEventoClinicoService chamadoEventoClinicoService;

    @Autowired
    private AtendimentoService atendimentoService;

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

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO', 'PACIENTE', 'RECEPCAO')")
    @GetMapping("/detalhes/{id}")
    public ResponseEntity<DetalheChamadoResponseDTO> consultarDetalhesChamado(@PathVariable Long id) {
        Chamado chamado = chamadoService.consultarDetalhesChamado(id);

        Long atendimentoId = null;
        try {
            atendimentoId = atendimentoService.buscarAtendimentoPorChamadoId(id).getId();
        } catch (Exception ignored) {}

        return ResponseEntity.ok(chamadoMapper.toDetalheDTO(
                chamado,
                chamado.getChamadoSintomas(),
                chamado.getChamadoEventoClinicos(),
                chamado.getSinaisAlertas(),
                atendimentoId
        ));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO', 'PACIENTE', 'RECEPCAO')")
    @GetMapping
    public ResponseEntity<List<FilaEsperaResponseDTO>> consultarChamados() {
        List<Chamado> chamados = chamadoService.listarTodosChamadosEmEsperaAndEmAtendimento();

        List<FilaEsperaResponseDTO> filaEsperaDto = chamados.stream()
                .map(chamadoMapper::toFilaEsperaDTO)
                .toList();

        return ResponseEntity.ok(filaEsperaDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO', 'PACIENTE')")
    @PostMapping("/{chamadoId}/evento")
    public ResponseEntity<Void> salvarEventosClinicos (@PathVariable Long chamadoId, @RequestBody ChamadoEventoClinicoRequestDTO chamadoEventoClinico) {
        chamadoEventoClinicoService.salvarEventosClinicosByChamado(chamadoId, chamadoEventoClinico.eventosIds());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO', 'RECEPCAO')")
    @PutMapping("/{chamadoId}/alterar-prioridade")
    public ResponseEntity<AlterarPrioridadeResponseDTO> alterarPrioridade (@PathVariable Long chamadoId, @RequestBody AlterarPrioridadeRequestDTO prioridadeChamado) {
        Chamado prioridadeAlterada = chamadoService.alterarPrioridade(chamadoId, prioridadeChamado.prioridadeChamado());

        return ResponseEntity.ok(chamadoMapper.toAlterarPrioridadeDTO(prioridadeAlterada));
    }
}

package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.encaminharChamado.EncaminharChamadoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.encaminharChamado.EncaminharChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.EncaminharChamadoMapper;
import com.Unifor.MedMaisFacil.mapper.EspecialidadeMapper;
import com.Unifor.MedMaisFacil.models.EncaminharChamado;
import com.Unifor.MedMaisFacil.models.EspecialidadeMedico;
import com.Unifor.MedMaisFacil.service.ChamadoService;
import com.Unifor.MedMaisFacil.service.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'MEDICO', 'RECEPCAO')")
@RestController
@RequestMapping("/v1/especialidade")
@RequiredArgsConstructor
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    private final EspecialidadeMapper especialidadeMapper;

    private final ChamadoService chamadoService;

    private final EncaminharChamadoMapper encaminharChamadoMapper;

    @PostMapping
    public ResponseEntity<EspecialidadeMedicoResponseDTO> cadastrarEspecialidade (@RequestBody EspecialidadeMedicoRequestDTO dto) {
        EspecialidadeMedico especialidadeMedicoSalvo = especialidadeService.salvarEspecialidade(especialidadeMapper.toModel(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeMapper.toDTO(especialidadeMedicoSalvo));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'RECEPCAO')")
    @PutMapping("/chamado/{chamadoId}/encaminhar")
    public ResponseEntity<EncaminharChamadoResponseDTO> encaminharChamadoParaEspecialidade (@PathVariable Long chamadoId, @RequestBody EncaminharChamadoRequestDTO dto) {
        EncaminharChamado chamadoEncaminhado = chamadoService.encaminharChamado(chamadoId, encaminharChamadoMapper.toModel(dto));

        return ResponseEntity.ok(encaminharChamadoMapper.toDTO(chamadoEncaminhado));
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadeMedicoResponseDTO>> consultarTodasEspecialidadesCadastradas () {
        List<EspecialidadeMedico> especialidadesMedicosListados = especialidadeService.listarEspecialidades();

        return ResponseEntity.ok(especialidadesMedicosListados.stream().map(especialidadeMapper::toDTO).toList());
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<EspecialidadeMedicoResponseDTO> editarEspecialidade (@PathVariable Long id, @RequestBody EspecialidadeMedicoRequestDTO dto) {
        EspecialidadeMedico especialidadeAtualizada = especialidadeService.atualizarDadosEspecialidade(id, especialidadeMapper.toModel(dto));

        return ResponseEntity.ok(especialidadeMapper.toDTO(especialidadeAtualizada));
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletarEspecialidade (@PathVariable Long id) {
        especialidadeService.deletarEspecialidadeById(id);
        return ResponseEntity.noContent().build();
    }
}
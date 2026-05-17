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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
}
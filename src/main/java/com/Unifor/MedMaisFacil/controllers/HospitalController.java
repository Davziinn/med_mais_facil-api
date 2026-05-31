package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.hospital.*;
import com.Unifor.MedMaisFacil.mapper.HospitalMapper;
import com.Unifor.MedMaisFacil.models.Hospital;
import com.Unifor.MedMaisFacil.models.HospitalMetrica;
import com.Unifor.MedMaisFacil.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
@RestController
@RequestMapping("/v1/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalMapper hospitalMapper;

    @PostMapping
    public ResponseEntity<HospitalResponseDTO> cadastrarHospital (@RequestBody HospitalRequestDTO hospitalRequestDTO) {
        Hospital hospitalCadastrado = hospitalService.salvarHospital(hospitalMapper.toModel(hospitalRequestDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalMapper.toDTO(hospitalCadastrado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDTO> consultarHospital (@PathVariable Long id) {
        Hospital hospitalEncontrado = hospitalService.buscarHospitalById(id);

        return ResponseEntity.ok(hospitalMapper.toDTO(hospitalEncontrado));
    }

    @GetMapping
    public ResponseEntity<List<HospitalResponseDTO>> consultarHospitais () {
        List<Hospital> hospitaisListados = hospitalService.listarHospitais();

        return ResponseEntity.ok(hospitaisListados.stream().map(hospitalMapper::toDTO).toList());
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<HospitalResponseDTO> editarDadosHospital (@PathVariable Long id, @RequestBody HospitalRequestDTO dto) {
        Hospital hospitalAtualizado = hospitalService.atualizarHospital(id, hospitalMapper.toModel(dto));

        return ResponseEntity.ok(hospitalMapper.toDTO(hospitalAtualizado));
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletarHospital (@PathVariable Long id) {
        hospitalService.deletarHospital(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/metricas")
    public ResponseEntity<HospitalMetricaResponseDTO> consultarMetricasHospital () {
        HospitalMetrica consulta = hospitalService.buscarMetricas();

        return ResponseEntity.ok(hospitalMapper.toMetricasDTO(consulta));
    }
}

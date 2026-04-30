package com.Unifor.MedMaisFacil.dtos.detalheChamado;

import com.Unifor.MedMaisFacil.dtos.paciente.PacienteResponseDTO;
import com.Unifor.MedMaisFacil.dtos.sintomaChamado.SintomaChamadoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record DetalheChamadoResponseDTO (
        Long id,
        String senha,

        String statusChamado,
        String prioridadeChamado,
        LocalDateTime dataAbertura,

        PacienteResponseDTO paciente,

        String queixa,

        List<SintomaChamadoResponseDTO> sintomas
){
}

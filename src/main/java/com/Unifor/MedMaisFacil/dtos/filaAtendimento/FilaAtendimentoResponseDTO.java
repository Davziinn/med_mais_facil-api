package com.Unifor.MedMaisFacil.dtos.filaAtendimento;

import com.Unifor.MedMaisFacil.dtos.paciente.PacienteResponseDTO;


public record FilaAtendimentoResponseDTO(
        Long id,
        String senha,
        PacienteResponseDTO paciente,
        String queixa,
        String statusChamado,
        String prioridadeChamado,
        Long tempoEspera
) {
}

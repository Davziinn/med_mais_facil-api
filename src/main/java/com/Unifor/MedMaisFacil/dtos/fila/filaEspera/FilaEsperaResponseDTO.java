package com.Unifor.MedMaisFacil.dtos.fila.filaEspera;

import com.Unifor.MedMaisFacil.dtos.paciente.PacienteResponseDTO;


public record FilaEsperaResponseDTO(
        Long id,
        String senha,
        PacienteResponseDTO paciente,
        String queixa,
        String statusChamado,
        String prioridadeChamado,
        Long tempoEspera
) {
}

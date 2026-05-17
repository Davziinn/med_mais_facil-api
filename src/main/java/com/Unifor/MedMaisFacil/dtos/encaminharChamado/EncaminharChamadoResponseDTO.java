package com.Unifor.MedMaisFacil.dtos.encaminharChamado;

import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoResponseDTO;
import com.Unifor.MedMaisFacil.enums.StatusChamado;

public record EncaminharChamadoResponseDTO(
        Long id,
        StatusChamado statusChamado,
        EspecialidadeMedicoResponseDTO especialidadeMedico
) {
}

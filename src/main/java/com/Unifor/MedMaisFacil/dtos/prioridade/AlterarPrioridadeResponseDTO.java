package com.Unifor.MedMaisFacil.dtos.prioridade;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;

public record AlterarPrioridadeResponseDTO(
        Long id,
        PrioridadeChamado prioridadeChamado
) {
}

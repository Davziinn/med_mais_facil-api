package com.Unifor.MedMaisFacil.dtos.chamadoEventoClinico;

import java.util.List;

public record ChamadoEventoClinicoRequestDTO(
        List<Long> eventosIds
) {
}

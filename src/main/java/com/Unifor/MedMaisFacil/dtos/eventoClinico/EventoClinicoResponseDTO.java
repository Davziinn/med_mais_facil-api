package com.Unifor.MedMaisFacil.dtos.eventoClinico;

import com.Unifor.MedMaisFacil.enums.Severidade;

public record EventoClinicoResponseDTO (
        Long id,
        String nomeEvento,
        String descricao,
        Severidade severidade
) {
}

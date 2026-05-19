package com.Unifor.MedMaisFacil.dtos.eventoClinico;

import com.Unifor.MedMaisFacil.enums.Severidade;

public record EventoClinicoRequestDTO(
        String nomeEvento,
        String descricao,
        Severidade severidade
) {
}

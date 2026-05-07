package com.Unifor.MedMaisFacil.dtos.sinaisAlerta;

import com.Unifor.MedMaisFacil.enums.Severidade;

public record SinaisAlertaResponseDTO(
        String descricao,
        Severidade severidade
) {
}

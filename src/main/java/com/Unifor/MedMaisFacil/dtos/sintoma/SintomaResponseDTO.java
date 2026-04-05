package com.Unifor.MedMaisFacil.dtos.sintoma;

public record SintomaResponseDTO(
        Long id,
        String descricao,
        Integer intensidade,
        String descricaoLivre
) {
}

package com.Unifor.MedMaisFacil.dtos.exame;

public record ExameResponseDTO(
        Long id,
        String nome,
        String descricao,
        Boolean ativo
) {
}

package com.Unifor.MedMaisFacil.dtos.sintoma;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;

public record SintomaResponseDTO (
        Long id,
        String descricao,
        PrioridadeChamado prioridadeSintoma,
        boolean ativo
){}

package com.Unifor.MedMaisFacil.dtos.sintoma;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;

public record SintomaRequestDTO (
        String descricao,
        PrioridadeChamado prioridadeSintoma,
        boolean ativo
){}

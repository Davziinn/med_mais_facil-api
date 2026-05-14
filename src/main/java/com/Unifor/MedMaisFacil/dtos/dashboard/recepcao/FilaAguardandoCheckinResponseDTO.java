package com.Unifor.MedMaisFacil.dtos.dashboard.recepcao;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;

public record FilaAguardandoCheckinResponseDTO (
        Long id,
        String senha,
        String nomePaciente,
        String queixa,
        PrioridadeChamado prioridadeChamado
){}

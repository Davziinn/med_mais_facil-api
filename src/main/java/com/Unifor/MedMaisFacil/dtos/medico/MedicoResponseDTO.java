package com.Unifor.MedMaisFacil.dtos.medico;

import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoResponseDTO;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String crm,
        EspecialidadeMedicoResponseDTO especialidade,
        String sexo,
        int idade
) {}

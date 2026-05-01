package com.Unifor.MedMaisFacil.dtos.medico;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String crm,
        String especialidade,
        String sexo,
        int idade
) {}

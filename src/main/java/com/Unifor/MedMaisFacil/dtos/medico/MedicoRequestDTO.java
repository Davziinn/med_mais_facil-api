package com.Unifor.MedMaisFacil.dtos.medico;

import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoRequestDTO;

import java.time.LocalDate;

public record MedicoRequestDTO(
        String crm,
        EspecialidadeMedicoRequestDTO especialidade,
        String sexo,
        LocalDate dataNascimento,
        Long usuarioId
) {}
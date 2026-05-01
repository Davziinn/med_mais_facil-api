package com.Unifor.MedMaisFacil.dtos.medico;

import java.time.LocalDate;

public record MedicoRequestDTO(
        String nome,
        String crm,
        String especialidade,
        String sexo,
        LocalDate dataNascimento
) {}
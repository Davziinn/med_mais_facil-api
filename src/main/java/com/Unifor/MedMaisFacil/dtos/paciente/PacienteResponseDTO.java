package com.Unifor.MedMaisFacil.dtos.paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PacienteResponseDTO (
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String sexo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}

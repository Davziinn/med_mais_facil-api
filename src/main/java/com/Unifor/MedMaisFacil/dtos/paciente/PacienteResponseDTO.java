package com.Unifor.MedMaisFacil.dtos.paciente;

import java.time.LocalDateTime;

public record PacienteResponseDTO (
        Long id,
        String nome,
        String cpf,
        int idade,
        String sexo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}

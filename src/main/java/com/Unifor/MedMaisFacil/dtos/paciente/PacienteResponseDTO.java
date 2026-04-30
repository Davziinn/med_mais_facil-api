package com.Unifor.MedMaisFacil.dtos.paciente;

public record PacienteResponseDTO (
        Long id,
        String nome,
        String cpf,
        int idade,
        String sexo
) {}

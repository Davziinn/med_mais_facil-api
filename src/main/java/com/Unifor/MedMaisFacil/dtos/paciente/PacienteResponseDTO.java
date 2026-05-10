package com.Unifor.MedMaisFacil.dtos.paciente;

import java.util.List;

public record PacienteResponseDTO (
        Long id,
        String nome,
        String cpf,
        int idade,
        String sexo,
        List<String> condicoesPreexistentes
) {}

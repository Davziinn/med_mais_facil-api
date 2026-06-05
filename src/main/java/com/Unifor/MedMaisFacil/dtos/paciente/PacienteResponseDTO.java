package com.Unifor.MedMaisFacil.dtos.paciente;

import java.util.List;

public record PacienteResponseDTO (
        Long id,
        String nome,
        String cpf,
        String telefone,
        int idade,
        String sexo,
        String convenio,
        List<String> condicoesPreexistentes
) {}

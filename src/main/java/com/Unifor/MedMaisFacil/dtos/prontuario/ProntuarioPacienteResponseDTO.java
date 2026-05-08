package com.Unifor.MedMaisFacil.dtos.prontuario;

import java.util.List;

public record ProntuarioPacienteResponseDTO(
        Long pacienteId,
        String nomePaciente,
        String cpf,
        Integer idade,
        String sexo,
        List<String> condicoesPreexistentes, // mock por enquanto
        List<ProntuarioItemResponseDTO> historico
) {}

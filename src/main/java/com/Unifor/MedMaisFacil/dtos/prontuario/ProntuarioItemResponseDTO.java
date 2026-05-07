package com.Unifor.MedMaisFacil.dtos.prontuario;

import java.time.LocalDateTime;
import java.util.List;

public record ProntuarioItemResponseDTO(
        Long atendimentoId,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String hipoteseDiagnostica,
        String conduta,
        String anamnese,
        String nomeMedico,
        String diagnostico,
        String observacoes,
        List<String> medicamentos,
        List<String> exames
) {}

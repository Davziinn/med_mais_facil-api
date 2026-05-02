package com.Unifor.MedMaisFacil.dtos.salvarAtendimento;

import java.time.LocalDateTime;

public record SalvarAtendimentoResponseDTO(
        Long id,
        String anamnese,
        String exameFisico,
        String hipoteseDiagnostica,
        String cidDoenca,
        String conduta,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long chamadoId,
        Long medicoId
) {
}

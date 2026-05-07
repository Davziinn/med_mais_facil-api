package com.Unifor.MedMaisFacil.dtos.atendimento.encerrarAtendimento;

import java.time.LocalDateTime;

public record EncerrarAtendimentoResponseDTO(
        Long id,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long chamadoId,
        Long medicoId
) {
}

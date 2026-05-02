package com.Unifor.MedMaisFacil.dtos.encerrarAtendimento;

import java.time.LocalDateTime;

public record EncerrarAtendimentoResponseDTO(
        Long id,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long chamadoId,
        Long medicoId
) {
}

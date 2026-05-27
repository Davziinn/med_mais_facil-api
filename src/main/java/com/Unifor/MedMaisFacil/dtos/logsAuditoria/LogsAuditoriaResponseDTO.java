package com.Unifor.MedMaisFacil.dtos.logsAuditoria;

import java.time.LocalDateTime;

public record LogsAuditoriaResponseDTO(
        Long id,
        Long usuarioId,
        String nomeUsuario,
        String acao,
        String modulo,
        String detalhes,
        LocalDateTime criadoEm
) {
}

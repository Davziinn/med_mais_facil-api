package com.Unifor.MedMaisFacil.dtos.configuracao;

public record ConfiguracaoResponseDTO(
        Long id,
        Integer tempoLimiteChamado,
        Integer quantidadeMaximaFila,
        Boolean chamadaAutomatica,
        String statusGeral,
        String mensagemPaciente,
        Boolean notificacoesPush
) {
}

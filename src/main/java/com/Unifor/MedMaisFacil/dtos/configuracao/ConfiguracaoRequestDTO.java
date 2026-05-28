package com.Unifor.MedMaisFacil.dtos.configuracao;

public record ConfiguracaoRequestDTO(
        Integer tempoLimiteChamado,
        Integer quantidadeMaximaFila,
        Boolean chamadaAutomatica,
        String statusGeral,
        String mensagemPaciente,
        Boolean notificacoesPush
) {
}

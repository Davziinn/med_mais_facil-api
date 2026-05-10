package com.Unifor.MedMaisFacil.dtos.atendimento.iniciarAtendimento;

import com.Unifor.MedMaisFacil.enums.StatusChamado;

public record IniciarAtendimentoResponseDTO(
        Long id,
        StatusChamado statusChamado
) {
}

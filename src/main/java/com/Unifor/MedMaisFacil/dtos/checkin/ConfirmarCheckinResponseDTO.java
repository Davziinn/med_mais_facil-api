package com.Unifor.MedMaisFacil.dtos.checkin;

import com.Unifor.MedMaisFacil.enums.StatusChamado;

public record ConfirmarCheckinResponseDTO(
        Long chamadoId,
        StatusChamado statusChamado
) {
}

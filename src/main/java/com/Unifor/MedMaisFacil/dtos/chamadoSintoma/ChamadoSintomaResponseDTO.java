package com.Unifor.MedMaisFacil.dtos.chamadoSintoma;

import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.sintoma.SintomaResponseDTO;

import java.time.LocalDateTime;

public record ChamadoSintomaResponseDTO(
        Long id,
        Integer intensidade,
        String descricaoLivre,
        LocalDateTime dataRegistro,
        ChamadoResponseDTO chamadoResponseDTO,
        SintomaResponseDTO sintomaResponseDTO
) {
}

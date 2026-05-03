package com.Unifor.MedMaisFacil.dtos.prescricaoMedicamento;

public record PrescricaoMedicamentoResponseDTO(
        Long id,
        String nome,
        String dose,
        String frequencia,
        String duracao,
        String via
) {
}

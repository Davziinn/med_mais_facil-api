package com.Unifor.MedMaisFacil.dtos.prescricaoMedicamento;

public record PrescricaoMedicamentoRequestDTO(
        String nome,
        String dose,
        String frequencia,
        String duracao,
        String via
) {
}

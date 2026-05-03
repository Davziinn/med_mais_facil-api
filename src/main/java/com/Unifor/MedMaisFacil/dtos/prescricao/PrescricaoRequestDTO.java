package com.Unifor.MedMaisFacil.dtos.prescricao;

import com.Unifor.MedMaisFacil.dtos.prescricaoMedicamento.PrescricaoMedicamentoRequestDTO;

import java.util.List;

public record PrescricaoRequestDTO(
        String orientacoes,
        String retornoConsulta,
        String exames,
        List<PrescricaoMedicamentoRequestDTO> medicamentos
) {
}

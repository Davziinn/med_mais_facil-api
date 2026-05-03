package com.Unifor.MedMaisFacil.dtos.prescricao;

import com.Unifor.MedMaisFacil.dtos.prescricaoMedicamento.PrescricaoMedicamentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.salvarAtendimento.SalvarAtendimentoResponseDTO;

import java.util.List;

public record PrescricaoResponseDTO(
        Long id,
        String orientacoes,
        String retornoConsulta,
        String exames,
        SalvarAtendimentoResponseDTO atendimento,
        List<PrescricaoMedicamentoResponseDTO> medicamentos
) {
}

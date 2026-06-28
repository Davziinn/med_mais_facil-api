package com.Unifor.MedMaisFacil.dtos.guiaExame;

import com.Unifor.MedMaisFacil.dtos.exame.ExameResponseDTO;
import com.Unifor.MedMaisFacil.enums.Convenio;

import java.time.LocalDate;
import java.util.List;

public record GuiaMedicaResponseDTO(
        Long id,
        String cidExame,
        String indicacaoClinica,
        String numeroGuia,
        LocalDate dataSolicitacao,
        Convenio convenio,
        String observacoes,
        Long atendimentoId,
        List<ExameResponseDTO> exames
) {
}

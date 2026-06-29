package com.Unifor.MedMaisFacil.dtos.guiaExame;

import com.Unifor.MedMaisFacil.enums.Convenio;

import java.util.List;

public record GuiaMedicaRequestDTO(
        String cidExame,
        String indicacaoClinica,
        Convenio convenio,
        String observacoes,
        Long atendimentoId,
        List<Long> examesIds
) {
}

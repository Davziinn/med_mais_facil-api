package com.Unifor.MedMaisFacil.dtos.guiaExame;

import com.Unifor.MedMaisFacil.dtos.exame.ExameRequestDTO;
import com.Unifor.MedMaisFacil.enums.Convenio;
import com.Unifor.MedMaisFacil.enums.StatusGuiaMedica;

import java.util.List;

public record GuiaMedicaRequestDTO(
        String cidExame,
        String indicacaoClinica,
        String numeroGuia,
        Convenio convenio,
        StatusGuiaMedica statusGuiaMedica,
        String observacoes,
        Long atendimentoId,
        List<ExameRequestDTO> exames
) {
}

package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.exame.ExameRequestDTO;
import com.Unifor.MedMaisFacil.dtos.exame.ExameResponseDTO;
import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaRequestDTO;
import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaResponseDTO;
import com.Unifor.MedMaisFacil.entity.ExameEntity;
import com.Unifor.MedMaisFacil.models.Exame;
import com.Unifor.MedMaisFacil.models.GuiaMedica;

public interface ExameMapper {

    Exame toModel (ExameEntity entity);

    ExameEntity toEntity (Exame model);

    Exame toModel (ExameRequestDTO dto);

    ExameResponseDTO toDTO (Exame model);
}

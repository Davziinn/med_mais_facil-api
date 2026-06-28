package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaExameRequestDTO;
import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaRequestDTO;
import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaResponseDTO;
import com.Unifor.MedMaisFacil.entity.GuiaMedicaEntity;
import com.Unifor.MedMaisFacil.models.GuiaMedica;

public interface GuiaMedicaMapper {

    GuiaMedica toModel (GuiaMedicaEntity entity);

    GuiaMedicaEntity toEntity (GuiaMedica model);

    GuiaMedica toModel (GuiaMedicaRequestDTO dto);

    GuiaMedicaResponseDTO toDTO (GuiaMedica model);
}

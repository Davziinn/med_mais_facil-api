package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.GuiaMedicaEntity;
import com.Unifor.MedMaisFacil.models.GuiaMedica;

public interface GuiaMedicaMapper {

    GuiaMedica toModel (GuiaMedicaEntity entity);

    GuiaMedicaEntity toEntity (GuiaMedica model);
}

package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.sintoma.SintomaRequestDTO;
import com.Unifor.MedMaisFacil.dtos.sintoma.SintomaResponseDTO;
import com.Unifor.MedMaisFacil.entity.SintomaEntity;
import com.Unifor.MedMaisFacil.models.Sintoma;

public interface SintomaMapper {

    Sintoma toModel (SintomaEntity entity);

    SintomaEntity toEntity (Sintoma model);

    Sintoma toModel (SintomaRequestDTO sintomaRequestDTO);

    SintomaResponseDTO toDTO (Sintoma model);

    Sintoma toModel(SintomaResponseDTO sintomaResponseDTO);
}

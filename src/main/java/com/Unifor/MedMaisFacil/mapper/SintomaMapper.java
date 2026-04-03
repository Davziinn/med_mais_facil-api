package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.SintomaEntity;
import com.Unifor.MedMaisFacil.models.Sintoma;

public interface SintomaMapper {

    Sintoma toModel (SintomaEntity entity);

    SintomaEntity toEntity (Sintoma model);
}

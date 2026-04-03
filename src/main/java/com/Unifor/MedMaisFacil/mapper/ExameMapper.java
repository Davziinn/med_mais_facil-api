package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ExameEntity;
import com.Unifor.MedMaisFacil.models.Exame;

public interface ExameMapper {

    Exame toModel (ExameEntity entity);

    ExameEntity toEntity (Exame model);
}

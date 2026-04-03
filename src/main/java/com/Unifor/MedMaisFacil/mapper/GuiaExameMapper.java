package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.GuiaExameEntity;
import com.Unifor.MedMaisFacil.models.GuiaExame;

public interface GuiaExameMapper {

    GuiaExame toModel (GuiaExameEntity entity);

    GuiaExameEntity toEntity (GuiaExame model);
}

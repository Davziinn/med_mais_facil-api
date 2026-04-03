package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.AgendamentoExameEntity;
import com.Unifor.MedMaisFacil.models.AgendamentoExame;

public interface AgendamentoExameMapper {

    AgendamentoExame toModel (AgendamentoExameEntity entity);

    AgendamentoExameEntity toEntity (AgendamentoExame model);
}

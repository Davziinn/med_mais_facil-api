package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.models.Atendimento;

public interface AtendimentoMapper {

    Atendimento toModel (AtendimentoEntity entity);

    AtendimentoEntity toEntity (Atendimento model);
}

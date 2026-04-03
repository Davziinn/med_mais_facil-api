package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.models.Chamado;

public interface ChamadoMapper {

    Chamado toModel (ChamadoEntity entity);

    ChamadoEntity toEntity (Chamado model);
}

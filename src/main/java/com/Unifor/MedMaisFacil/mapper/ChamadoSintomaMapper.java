package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ChamadoSintomaEntity;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;

import java.util.List;

public interface ChamadoSintomaMapper {

    ChamadoSintoma toModel (ChamadoSintomaEntity entity);

    ChamadoSintomaEntity toEntity (ChamadoSintoma model);

    List<ChamadoSintoma> toModelList (List<ChamadoSintomaEntity> entities);
}

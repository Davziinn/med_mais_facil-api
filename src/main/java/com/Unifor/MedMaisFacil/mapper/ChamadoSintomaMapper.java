package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.chamadoSintoma.ChamadoSintomaResponseDTO;
import com.Unifor.MedMaisFacil.entity.ChamadoSintomaEntity;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;

import java.util.List;

public interface    ChamadoSintomaMapper {

    ChamadoSintoma toModel (ChamadoSintomaEntity entity);

    ChamadoSintomaEntity toEntity (ChamadoSintoma model);

    ChamadoSintoma toModel (ChamadoSintomaResponseDTO dto);

    ChamadoSintomaResponseDTO toDTO (ChamadoSintoma model);

    List<ChamadoSintoma> toModelList (List<ChamadoSintomaEntity> entities);
}

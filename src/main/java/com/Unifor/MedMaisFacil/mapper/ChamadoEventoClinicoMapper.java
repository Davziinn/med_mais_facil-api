package com.Unifor.MedMaisFacil.mapper;


import com.Unifor.MedMaisFacil.entity.ChamadoEventoClinicoEntity;
import com.Unifor.MedMaisFacil.models.ChamadoEventoClinico;

import java.util.List;

public interface ChamadoEventoClinicoMapper {

    ChamadoEventoClinico toModel (ChamadoEventoClinicoEntity entity);

    ChamadoEventoClinicoEntity toEntity (ChamadoEventoClinico model);

    List<ChamadoEventoClinico> toModelList(List<ChamadoEventoClinicoEntity> entities);

}

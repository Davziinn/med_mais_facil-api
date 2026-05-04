package com.Unifor.MedMaisFacil.mapper;


import com.Unifor.MedMaisFacil.entity.ChamadoEventoClinicoEntity;
import com.Unifor.MedMaisFacil.models.ChamadoEventoClinico;

public interface ChamadoEventoClinicoMapper {

    ChamadoEventoClinico toModel (ChamadoEventoClinicoEntity entity);

    ChamadoEventoClinicoEntity toEntity (ChamadoEventoClinico model);
}

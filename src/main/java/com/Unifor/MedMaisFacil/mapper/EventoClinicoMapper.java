package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.eventoClinico.EventoClinicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.eventoClinico.EventoClinicoResponseDTO;
import com.Unifor.MedMaisFacil.entity.EventoClinicoEntity;
import com.Unifor.MedMaisFacil.models.EventoClinico;

public interface EventoClinicoMapper {

    EventoClinico toModel (EventoClinicoEntity entity);

    EventoClinicoEntity toEntity (EventoClinico model);

    EventoClinico toModel (EventoClinicoRequestDTO dto);

    EventoClinicoResponseDTO toDTO (EventoClinico model);
}

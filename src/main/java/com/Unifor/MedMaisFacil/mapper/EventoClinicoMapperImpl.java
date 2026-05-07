package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.eventoClinico.EventoClinicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.eventoClinico.EventoClinicoResponseDTO;
import com.Unifor.MedMaisFacil.entity.EventoClinicoEntity;
import com.Unifor.MedMaisFacil.models.EventoClinico;
import org.springframework.stereotype.Component;

@Component
public class EventoClinicoMapperImpl implements EventoClinicoMapper{

    @Override
    public EventoClinico toModel(EventoClinicoEntity entity) {
        return EventoClinico.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .build();
    }

    @Override
    public EventoClinicoEntity toEntity(EventoClinico model) {
        return new EventoClinicoEntity(
                model.getId(),
                model.getDescricao()
        );
    }

    @Override
    public EventoClinico toModel(EventoClinicoRequestDTO dto) {
        return EventoClinico.builder()
                .descricao(dto.descricao())
                .build();
    }

    @Override
    public EventoClinicoResponseDTO toDTO(EventoClinico model) {
        return new EventoClinicoResponseDTO(
                model.getId(),
                model.getDescricao()
        );
    }
}

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
                .nomeEvento(entity.getNomeEvento())
                .descricao(entity.getDescricao())
                .severidade(entity.getSeveridade())
                .build();
    }

    @Override
    public EventoClinicoEntity toEntity(EventoClinico model) {
        return new EventoClinicoEntity(
                model.getId(),
                model.getNomeEvento(),
                model.getDescricao(),
                model.getSeveridade()
        );
    }

    @Override
    public EventoClinico toModel(EventoClinicoRequestDTO dto) {
        return EventoClinico.builder()
                .nomeEvento(dto.nomeEvento())
                .descricao(dto.descricao())
                .severidade(dto.severidade())
                .build();
    }

    @Override
    public EventoClinicoResponseDTO toDTO(EventoClinico model) {
        return new EventoClinicoResponseDTO(
                model.getId(),
                model.getNomeEvento(),
                model.getDescricao(),
                model.getSeveridade()
        );
    }
}

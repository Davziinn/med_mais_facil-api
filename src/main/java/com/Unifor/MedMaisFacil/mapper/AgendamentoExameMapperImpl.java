package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.AgendamentoExameEntity;
import com.Unifor.MedMaisFacil.models.AgendamentoExame;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoExameMapperImpl implements AgendamentoExameMapper{

    @Override
    public AgendamentoExame toModel(AgendamentoExameEntity entity) {
        return new AgendamentoExame(
                entity.getId(),
                entity.getDataHoraAgendamento(),
                entity.getStatus(),
                entity.getObservacoes(),
                entity.getGuiaExame().getId()
        );
    }

    @Override
    public AgendamentoExameEntity toEntity(AgendamentoExame model) {

        AgendamentoExameEntity entity = new AgendamentoExameEntity();

        entity.setId(model.getId());
        entity.setDataHoraAgendamento(model.getDataHoraAgendamento());
        entity.setStatus(model.getStatus());
        entity.setObservacoes(model.getObservacoes());

        // guiaExame deve ser buscado e setado no service
        return entity;

    }
}

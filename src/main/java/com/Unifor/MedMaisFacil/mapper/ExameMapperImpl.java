package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ExameEntity;
import com.Unifor.MedMaisFacil.models.Exame;
import org.springframework.stereotype.Component;

@Component
public class ExameMapperImpl implements ExameMapper {

    @Override
    public Exame toModel(ExameEntity entity) {
        return new Exame(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm()
        );
    }

    @Override
    public ExameEntity toEntity(Exame model) {
        return new ExameEntity(
                model.getId(),
                model.getNome(),
                model.getDescricao(),
                model.getCriadoEm(),
                model.getAtualizadoEm()
        );
    }
}

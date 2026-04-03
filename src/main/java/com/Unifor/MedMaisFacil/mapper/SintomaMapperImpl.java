package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.SintomaEntity;
import com.Unifor.MedMaisFacil.models.Sintoma;
import org.springframework.stereotype.Component;

@Component
public class SintomaMapperImpl implements SintomaMapper{

    @Override
    public Sintoma toModel(SintomaEntity entity) {
        return new Sintoma(
                entity.getId(),
                entity.getDescricao()
        );
    }

    @Override
    public SintomaEntity toEntity(Sintoma model) {
        return new SintomaEntity(
                model.getId(),
                model.getDescricao()
        );
    }
}

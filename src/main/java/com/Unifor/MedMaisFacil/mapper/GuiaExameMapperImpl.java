package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.GuiaExameEntity;
import com.Unifor.MedMaisFacil.models.GuiaExame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuiaExameMapperImpl implements GuiaExameMapper {

    @Autowired
    private GuiaMedicaMapper guiaMedicaMapper;

    @Autowired
    private ExameMapper exameMapper;

    @Override
    public GuiaExame toModel(GuiaExameEntity entity) {
        return GuiaExame.builder()
                .id(entity.getId())
                .guia(entity.getGuia() != null
                        ? guiaMedicaMapper.toModel(entity.getGuia())
                        : null
                )
                .exame(entity.getExame() != null
                        ? exameMapper.toModel(entity.getExame())
                        : null
                )
                .build();
    }

    @Override
    public GuiaExameEntity toEntity(GuiaExame model) {
        return GuiaExameEntity.builder()
                .id(model.getId())
                .guia(model.getGuia() != null
                        ? guiaMedicaMapper.toEntity(model.getGuia())
                        : null
                )
                .exame(model.getExame() != null
                        ? exameMapper.toEntity(model.getExame())
                        : null
                )
                .build();
    }
}

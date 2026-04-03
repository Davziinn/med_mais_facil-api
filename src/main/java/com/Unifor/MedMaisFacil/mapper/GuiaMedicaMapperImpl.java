package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.GuiaMedicaEntity;
import com.Unifor.MedMaisFacil.models.GuiaMedica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuiaMedicaMapperImpl implements GuiaMedicaMapper {

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Override
    public GuiaMedica toModel(GuiaMedicaEntity entity) {
        return GuiaMedica.builder()
                .id(entity.getId())
                .numeroGuia(entity.getNumeroGuia())
                .dataSolicitacao(entity.getDataSolicitacao())
                .convenio(entity.getConvenio())
                .observacoes(entity.getObservacoes())
                .atendimento(entity.getAtendimento() != null
                        ? atendimentoMapper.toModel(entity.getAtendimento())
                        : null
                )
                .build();
    }

    @Override
    public GuiaMedicaEntity toEntity(GuiaMedica model) {
        return GuiaMedicaEntity.builder()
                .id(model.getId())
                .numeroGuia(model.getNumeroGuia())
                .dataSolicitacao(model.getDataSolicitacao())
                .convenio(model.getConvenio())
                .observacoes(model.getObservacoes())
                .atendimento(model.getAtendimento() != null
                        ? atendimentoMapper.toEntity(model.getAtendimento())
                        : null
                )
                .build();
    }
}

package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.models.Atendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtendimentoMapperImpl implements AtendimentoMapper {

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private ChamadoMapper chamadoMapper;

    @Override
    public Atendimento toModel(AtendimentoEntity entity) {

        Atendimento atendimentoModel = new Atendimento();

        atendimentoModel.setId(entity.getId());
        atendimentoModel.setObservacoes(entity.getObservacoes());
        atendimentoModel.setDataInicio(entity.getDataInicio());
        atendimentoModel.setDataFim(entity.getDataFim());
        atendimentoModel.setChamado(entity.getChamado() != null
                ? chamadoMapper.toModel(entity.getChamado())
                : null
        );
        atendimentoModel.setMedico(entity.getMedico() != null
                ? medicoMapper.toModel(entity.getMedico())
                : null
        );

        return atendimentoModel;
    }

    @Override
    public AtendimentoEntity toEntity(Atendimento model) {
        return new AtendimentoEntity(
                model.getId(),
                model.getObservacoes(),
                model.getDataInicio(),
                model.getDataFim(),
                model.getChamado() != null
                        ? chamadoMapper.toEntity(model.getChamado())
                        : null,
                model.getMedico() != null
                        ? medicoMapper.toEntity(model.getMedico())
                        : null
        );
    }
}

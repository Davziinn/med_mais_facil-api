package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ProntuarioEntity;
import com.Unifor.MedMaisFacil.models.Prontuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProntuarioMapperImpl implements ProntuarioMapper {

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Override
    public Prontuario toModel(ProntuarioEntity entity) {

        Prontuario prontuarioModel = new Prontuario();

        prontuarioModel.setId(entity.getId());
        prontuarioModel.setDiagnostico(entity.getDiagnostico());
        prontuarioModel.setObservacoes(entity.getObservacoes());
        prontuarioModel.setPrescricao(entity.getPrescricao());
        prontuarioModel.setDataRegistro(entity.getDataRegistro());
        prontuarioModel.setAtualizadoEm(entity.getAtualizadoEm());
        prontuarioModel.setAtendimento(entity.getAtendimento() != null ? atendimentoMapper.toModel(entity.getAtendimento()) : null);

        return prontuarioModel;
    }

    @Override
    public ProntuarioEntity toEntity(Prontuario model) {

        ProntuarioEntity prontuarioEntity = new ProntuarioEntity();

        prontuarioEntity.setId(model.getId());
        prontuarioEntity.setDiagnostico(model.getDiagnostico());
        prontuarioEntity.setObservacoes(model.getObservacoes());
        prontuarioEntity.setPrescricao(model.getPrescricao());
        prontuarioEntity.setDataRegistro(model.getDataRegistro());
        prontuarioEntity.setAtualizadoEm(model.getAtualizadoEm());
        prontuarioEntity.setAtendimento(model.getAtendimento() != null
                ? atendimentoMapper.toEntity(model.getAtendimento())
                : null
        );

        return prontuarioEntity;
    }
}

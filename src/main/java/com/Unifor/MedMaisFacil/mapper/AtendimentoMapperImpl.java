package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.encerrarAtendimento.EncerrarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.iniciarAtendimento.IniciarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.salvarAtendimento.SalvarAtendimentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.salvarAtendimento.SalvarAtendimentoResponseDTO;
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
        atendimentoModel.setAnamnese(entity.getAnamnese());
        atendimentoModel.setExameFisico(entity.getExameFisico());
        atendimentoModel.setHipoteseDiagnostica(entity.getHipoteseDiagnostica());
        atendimentoModel.setCidDoenca(entity.getCidDoenca());
        atendimentoModel.setConduta(entity.getConduta());
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
    public Atendimento toModel(SalvarAtendimentoRequestDTO dto) {
        return Atendimento.builder()
                .anamnese(dto.anamnese())
                .exameFisico(dto.exameFisico())
                .hipoteseDiagnostica(dto.hipoteseDiagnostica())
                .cidDoenca(dto.cidDoenca())
                .conduta(dto.conduta())
                .build();
    }

    @Override
    public AtendimentoEntity toEntity(Atendimento model) {
        return new AtendimentoEntity(
                model.getId(),
                model.getAnamnese(),
                model.getExameFisico(),
                model.getHipoteseDiagnostica(),
                model.getCidDoenca(),
                model.getConduta(),
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

    @Override
    public SalvarAtendimentoResponseDTO toSalvarDTO(Atendimento model) {
        return new SalvarAtendimentoResponseDTO(
                model.getId(),
                model.getAnamnese(),
                model.getExameFisico(),
                model.getHipoteseDiagnostica(),
                model.getCidDoenca(),
                model.getConduta(),
                model.getDataInicio(),
                model.getDataFim(),
                model.getChamado().getId(),
                model.getMedico().getId()
        );
    }

    @Override
    public EncerrarAtendimentoResponseDTO toEncerrarDTO(Atendimento model) {
        return new EncerrarAtendimentoResponseDTO(
                model.getId(),
                model.getDataInicio(),
                model.getDataFim(),
                model.getChamado().getId(),
                model.getMedico().getId()
        );
    }

    @Override
    public IniciarAtendimentoResponseDTO toIniciarDTO(Atendimento model) {
        return new IniciarAtendimentoResponseDTO(
                model.getId()
        );
    }
}

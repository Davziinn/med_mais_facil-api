package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.atendimento.encerrarAtendimento.EncerrarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.historicoAtendimento.HistoricoAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.iniciarAtendimento.IniciarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.salvarAtendimento.SalvarAtendimentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.salvarAtendimento.SalvarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.utils.CalcularIdadeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<HistoricoAtendimentoResponseDTO> toHistoricoDTO(List<Atendimento> models) {
        return models.stream()
                .map(atendimento -> new HistoricoAtendimentoResponseDTO(
                        atendimento.getId(),
                        atendimento.getChamado().getPaciente().getId(),
//                        atendimento.getChamado().getSenha(),
                        atendimento.getChamado().getPaciente().getNome(),
                        CalcularIdadeUtils.calcular(atendimento.getChamado().getPaciente().getDataNascimento()),
                        atendimento.getChamado().getPaciente().getSexo(),
                        atendimento.getHipoteseDiagnostica(),
                        atendimento.getMedico().getNome(),
                        atendimento.getChamado().getPrioridadeChamado().name(),
                        atendimento.getChamado().getStatusChamado().name(),
                        atendimento.getDataInicio(),
                        atendimento.getDataFim()
                ))
                .toList();
    }

    @Override
    public IniciarAtendimentoResponseDTO toIniciarDTO(Atendimento model) {
        return new IniciarAtendimentoResponseDTO(
                model.getId(),
                model.getChamado().getStatusChamado()
        );
    }
}

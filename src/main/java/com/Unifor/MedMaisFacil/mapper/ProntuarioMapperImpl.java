package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.prontuario.ProntuarioItemResponseDTO;
import com.Unifor.MedMaisFacil.dtos.prontuario.ProntuarioPacienteResponseDTO;
import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import com.Unifor.MedMaisFacil.entity.ProntuarioEntity;
import com.Unifor.MedMaisFacil.models.Prontuario;
import com.Unifor.MedMaisFacil.utils.CalcularIdadeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public ProntuarioPacienteResponseDTO toDTO(
            PacienteEntity paciente,
            List<AtendimentoEntity> atendimentos,
            Map<Long, Optional<PrescricaoEntity>> prescricoesPorAtendimento,
            Map<Long, Optional<ProntuarioEntity>> prontuariosPorAtendimento
    ) {
        List<ProntuarioItemResponseDTO> historico = atendimentos.stream()
                .map(atendimento -> toItemDTO(
                        atendimento,
                        prescricoesPorAtendimento.get(atendimento.getId()),
                        prontuariosPorAtendimento.get(atendimento.getId())
                ))
                .toList();

        return new ProntuarioPacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                CalcularIdadeUtils.calcular(paciente.getDataNascimento()),
                paciente.getSexo(),
                List.of(),
                historico
        );
    }

    private ProntuarioItemResponseDTO toItemDTO(
            AtendimentoEntity atendimento,
            Optional<PrescricaoEntity> prescricao,
            Optional<ProntuarioEntity> prontuario
    ) {
        List<String> medicamentos = prescricao
                .map(p -> p.getMedicamentos().stream()
                        .map(m -> m.getNome() + " " + m.getDose() + " - " + m.getFrequencia())
                        .toList()
                )
                .orElse(List.of());

        List<String> exames = prescricao.filter(p -> p.getExames() != null).map(p -> Arrays.stream(p.getExames().split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList()).orElse(List.of());

        return new ProntuarioItemResponseDTO(
                atendimento.getId(),
                atendimento.getDataInicio(),
                atendimento.getDataFim(),
                atendimento.getHipoteseDiagnostica(),
                atendimento.getConduta(),
                atendimento.getAnamnese(),
                atendimento.getMedico().getNome(),
                prontuario.map(ProntuarioEntity::getDiagnostico).orElse(null),
                prontuario.map(ProntuarioEntity::getObservacoes).orElse(null),
                medicamentos,
                exames
        );
    }
}

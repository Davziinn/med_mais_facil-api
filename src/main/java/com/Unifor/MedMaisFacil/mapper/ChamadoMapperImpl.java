package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.chamado.*;
import com.Unifor.MedMaisFacil.dtos.detalheChamado.DetalheChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.filaAtendimento.FilaAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.sintomaChamado.SintomaChamadoResponseDTO;
import com.Unifor.MedMaisFacil.entity.*;
import com.Unifor.MedMaisFacil.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ChamadoMapperImpl implements ChamadoMapper {

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public Chamado toModel(ChamadoEntity entity) {
        return new Chamado(
                entity.getId(),
                entity.getDescricaoRelato(),
                entity.getStatusChamado(),
                entity.getPrioridadeChamado(),
                entity.getDataHoraChamado(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm(),
                entity.getPaciente() != null
                        ? pacienteMapper.toModel(entity.getPaciente())
                        : null,
                entity.getHospital() != null
                        ? hospitalMapper.toModel(entity.getHospital())
                        : null,
                entity.getChamadoSintomas() != null
                        ? entity.getChamadoSintomas().stream()
                        .map(s -> ChamadoSintoma.builder()
                                .id(s.getId())
                                .intensidade(s.getIntensidade())
                                .descricaoLivre(s.getDescricaoLivre())
                                .dataRegistro(s.getDataRegistro())
                                .sintoma(
                                        s.getSintoma() != null
                                                ? Sintoma.builder()
                                                .id(s.getSintoma().getId())
                                                .descricao(s.getSintoma().getDescricao())
                                                .build()
                                                : null
                                )
                                .build()
                        )
                        .toList()
                        : List.of()
        );
    }

    @Override
    public ChamadoEntity toEntity(Chamado model) {
        ChamadoEntity entity = new ChamadoEntity(
                model.getId(),
                model.getDescricaoRelato(),
                model.getStatusChamado(),
                model.getPrioridadeChamado(),
                model.getDataHoraChamado(),
                model.getCriadoEm(),
                model.getAtualizadoEm(),
                model.getPaciente() != null
                        ? pacienteMapper.toEntity(model.getPaciente())
                        : null,
                model.getHospital() != null
                        ? hospitalMapper.toEntity(model.getHospital())
                        : null,
                null
        );

        if (model.getChamadoSintomas() != null) {
            List<ChamadoSintomaEntity> sintomas = model.getChamadoSintomas().stream()
                    .map(s -> {
                        ChamadoSintomaEntity entitySintoma = new ChamadoSintomaEntity();

                        entitySintoma.setIntensidade(s.getIntensidade());
                        entitySintoma.setDescricaoLivre(s.getDescricaoLivre());

                        entitySintoma.setChamado(entity);

                        if (s.getSintoma() != null) {
                            entitySintoma.setSintoma(
                                    new SintomaEntity(
                                            s.getSintoma().getId(),
                                            s.getSintoma().getDescricao()
                                    )
                            );
                        }

                        return entitySintoma;
                    })
                    .toList();

            entity.setChamadoSintomas(sintomas);
        }

        return entity;
    }

    @Override
    public Chamado toModel(ChamadoRequestDTO dto) {
        return Chamado.builder()
                .descricaoRelato(dto.descricaoRelato())
                .paciente(Paciente.builder().id(dto.pacienteId()).build())
                .hospital(Hospital.builder().id(dto.hospitalId()).build())
                .build();
    }

    @Override
    public Chamado toModel(ChamadoResponseDTO dto) {
        return Chamado.builder()
                .id(dto.id())
                .descricaoRelato(dto.descricaoRelato())
                .statusChamado(dto.statusChamado())
                .prioridadeChamado(dto.prioridadeChamado())
                .dataHoraChamado(dto.dataHoraChamado())
                .criadoEm(dto.criadoEm())
                .atualizadoEm(dto.atualizadoEm())
                .paciente(Paciente.builder()
                        .id(dto.pacienteId())
                        .nome(dto.nomePaciente())
                        .build())
                .hospital(Hospital.builder()
                        .id(dto.hospitalId())
                        .nome(dto.nomeHospital())
                        .build())
                .build();
    }

    @Override
    public ChamadoResponseDTO toDTO(Chamado model, List<ChamadoSintoma> chamadoSintomas) {
        return new ChamadoResponseDTO(
                model.getId(),
                model.getDescricaoRelato(),
                model.getStatusChamado(),
                model.getPrioridadeChamado(),
                model.getDataHoraChamado(),
                model.getCriadoEm(),
                model.getAtualizadoEm(),
                model.getPaciente().getId(),
                model.getPaciente().getNome(),
                model.getPaciente().getCpf(),
                model.getPaciente().getDataNascimento(),
                model.getPaciente().getSexo(),
                model.getHospital().getId(),
                model.getHospital().getNome(),
                chamadoSintomas.stream()
                        .map(sintoma -> new SintomaChamadoResponseDTO(
                                sintoma.getSintoma().getId(),
                                sintoma.getSintoma().getDescricao(),
                                sintoma.getIntensidade()
                        ))
                        .toList()
        );
    }

    @Override
    public DetalheChamadoResponseDTO toDetalheDTO(Chamado model, List<ChamadoSintoma> chamadoSintomas) {
        return new DetalheChamadoResponseDTO(
                model.getId(),
                gerarSenha(model),
                model.getStatusChamado().name(),
                model.getPrioridadeChamado().name(),
                model.getDataHoraChamado(),
                model.getPaciente() != null
                        ? pacienteMapper.toDTO(model.getPaciente())
                        : null,
                model.getDescricaoRelato(),
                chamadoSintomas.stream()
                        .map(sintoma -> new SintomaChamadoResponseDTO(
                                sintoma.getSintoma().getId(),
                                sintoma.getSintoma().getDescricao(),
                                sintoma.getIntensidade()
                        ))
                        .toList()
                );
    }

    @Override
    public FilaAtendimentoResponseDTO toFilaAtendimentoDTO(Chamado model) {
        return new FilaAtendimentoResponseDTO(
                model.getId(),
                gerarSenha(model),
                model.getPaciente() != null
                        ? pacienteMapper.toDTO(model.getPaciente())
                        : null,
                model.getDescricaoRelato(),
                model.getStatusChamado().name(),
                model.getPrioridadeChamado().name(),
                calcularTempoEspera(model.getDataHoraChamado())
        );
    }

    @Override
    public List<SintomaDoChamado> toSintomas(ChamadoRequestDTO dto) {
        return dto.sintomas().stream()
                .map(sintoma -> new SintomaDoChamado(
                        sintoma.getId(),
                        sintoma.getIntensidade(),
                        sintoma.getDescricaoLivre()
                )).toList();
    }

    private String gerarSenha(Chamado model) {
        return "A" + String.format("%03d", model.getId());
    }

    private Long calcularTempoEspera(LocalDateTime dataHora) {
        if (dataHora == null) return 0L;
        return Duration.between(dataHora, LocalDateTime.now()).toMinutes();
    }
}

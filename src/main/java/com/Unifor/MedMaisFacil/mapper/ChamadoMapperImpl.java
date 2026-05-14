package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.chamado.*;
import com.Unifor.MedMaisFacil.dtos.dashboard.recepcao.FilaAguardandoCheckinResponseDTO;
import com.Unifor.MedMaisFacil.dtos.detalheChamado.DetalheChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.eventoClinico.EventoClinicoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.fila.filaEmAtendimento.FilaEmAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.fila.filaEspera.FilaEsperaResponseDTO;
import com.Unifor.MedMaisFacil.dtos.sinaisAlerta.SinaisAlertaResponseDTO;
import com.Unifor.MedMaisFacil.dtos.sintomaChamado.SintomaChamadoResponseDTO;
import com.Unifor.MedMaisFacil.entity.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.utils.CalcularIdadeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
public class ChamadoMapperImpl implements ChamadoMapper {

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public Chamado toModel(ChamadoEntity entity) {
        return Chamado.builder()
                .id(entity.getId())
                .descricaoRelato(entity.getDescricaoRelato())
                .statusChamado(entity.getStatusChamado())
                .prioridadeChamado(entity.getPrioridadeChamado())
                .dataHoraChamado(entity.getDataHoraChamado())
                .criadoEm(entity.getCriadoEm())
                .atualizadoEm(entity.getAtualizadoEm())
                .paciente(entity.getPaciente() != null ? pacienteMapper.toModel(entity.getPaciente()) : null)
                .hospital(entity.getHospital() != null ? hospitalMapper.toModel(entity.getHospital()) : null)
                .chamadoSintomas(entity.getChamadoSintomas() != null ? entity.getChamadoSintomas().stream().map(chamadoSintoma -> {
                    return ChamadoSintoma.builder()
                            .id(chamadoSintoma.getId())
                            .intensidade(chamadoSintoma.getIntensidade())
                            .descricaoLivre(chamadoSintoma.getDescricaoLivre())
                            .tempoSintoma(chamadoSintoma.getTempoSintoma())
                            .frequencia(chamadoSintoma.getFrequencia())
                            .dataRegistro(chamadoSintoma.getDataRegistro())
                            .sintoma(chamadoSintoma.getSintoma() != null ? Sintoma.builder()
                                    .id(chamadoSintoma.getSintoma().getId())
                                    .descricao(chamadoSintoma.getSintoma().getDescricao())
                                    .build() : null)
                            .build();
                }).toList() : null)
                .build();
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
                        entitySintoma.setTempoSintoma(s.getTempoSintoma());
                        entitySintoma.setFrequencia(s.getFrequencia());

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
                                sintoma.getIntensidade(),
                                sintoma.getTempoSintoma(),
                                sintoma.getFrequencia()
                        ))
                        .toList()
        );
    }

    @Override
    public DetalheChamadoResponseDTO toDetalheDTO(Chamado model, List<ChamadoSintoma> chamadoSintomas, List<ChamadoEventoClinico> eventosClinicosChamado, List<SinaisAlerta> sinaisAlertas) {
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
                                sintoma.getIntensidade(),
                                sintoma.getTempoSintoma(),
                                sintoma.getFrequencia()
                        ))
                        .toList(),
                eventosClinicosChamado.stream()
                        .map(chamadoEventoClinico -> new EventoClinicoResponseDTO(
                                chamadoEventoClinico.getEventoClinico().getId(),
                                chamadoEventoClinico.getEventoClinico().getDescricao()
                        ))
                        .toList(),
                sinaisAlertas.stream()
                        .map(sinalAlerta -> new SinaisAlertaResponseDTO(
                                sinalAlerta.getDescricao(),
                                sinalAlerta.getSeveridade()
                        ))
                        .toList()
                );
    }

    @Override
    public FilaEmAtendimentoResponseDTO toFilaAtendimentoDTO(Chamado model) {
        return new FilaEmAtendimentoResponseDTO(
                model.getId(),
                model.getPaciente().getNome(),
                model.getDescricaoRelato(),
                CalcularIdadeUtils.calcular(model.getPaciente().getDataNascimento()),
                model.getStatusChamado()
        );
    }

    @Override
    public FilaAguardandoCheckinResponseDTO toFilaCheckinDTO(Chamado model) {

        String sintomaMaiorIntensidade = model.getChamadoSintomas()
                .stream()
                .max(Comparator.comparing(ChamadoSintoma::getIntensidade))
                .map(chamadoSintoma -> chamadoSintoma.getSintoma().getDescricao())
                .orElse("");

        return new FilaAguardandoCheckinResponseDTO(
                model.getId(),
                gerarSenha(model),
                model.getPaciente().getNome(),
                sintomaMaiorIntensidade,
                model.getPrioridadeChamado()
        );
    }

    @Override
    public FilaEsperaResponseDTO toFilaEsperaDTO(Chamado model) {
        return new FilaEsperaResponseDTO(
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
                        sintoma.getTempoSintoma(),
                        sintoma.getFrequencia()
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

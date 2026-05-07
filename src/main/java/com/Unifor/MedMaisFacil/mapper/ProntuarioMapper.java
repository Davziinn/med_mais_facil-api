package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.prontuario.ProntuarioPacienteResponseDTO;
import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import com.Unifor.MedMaisFacil.entity.ProntuarioEntity;
import com.Unifor.MedMaisFacil.models.Prontuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProntuarioMapper {

    Prontuario toModel (ProntuarioEntity entity);

    ProntuarioEntity toEntity (Prontuario model);

    ProntuarioPacienteResponseDTO toDTO(
            PacienteEntity paciente,
            List<AtendimentoEntity> atendimentos,
            Map<Long, Optional<PrescricaoEntity>> prescricoesPorAtendimento,
            Map<Long, Optional<ProntuarioEntity>> prontuariosPorAtendimento
    );
}

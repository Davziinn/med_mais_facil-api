package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.prontuario.ProntuarioPacienteResponseDTO;
import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import com.Unifor.MedMaisFacil.entity.ProntuarioEntity;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.Paciente;
import com.Unifor.MedMaisFacil.models.Prescricao;
import com.Unifor.MedMaisFacil.models.Prontuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProntuarioMapper {

    Prontuario toModel (ProntuarioEntity entity);

    ProntuarioEntity toEntity (Prontuario model);

    ProntuarioPacienteResponseDTO toDTO(
            Paciente paciente,
            List<Atendimento> atendimentos,
            Map<Long, Optional<Prescricao>> prescricoes,
            Map<Long, Optional<Prontuario>> prontuarios
    );
}

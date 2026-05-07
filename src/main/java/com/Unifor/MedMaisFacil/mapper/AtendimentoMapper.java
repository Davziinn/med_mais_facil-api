package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.atendimento.encerrarAtendimento.EncerrarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.historicoAtendimento.HistoricoAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.iniciarAtendimento.IniciarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.salvarAtendimento.SalvarAtendimentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.atendimento.salvarAtendimento.SalvarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.models.Atendimento;

import java.util.List;

public interface AtendimentoMapper {

    Atendimento toModel (AtendimentoEntity entity);

    Atendimento toModel (SalvarAtendimentoRequestDTO dto);

    AtendimentoEntity toEntity (Atendimento model);

    IniciarAtendimentoResponseDTO toIniciarDTO (Atendimento model);

    SalvarAtendimentoResponseDTO toSalvarDTO (Atendimento model);

    EncerrarAtendimentoResponseDTO toEncerrarDTO (Atendimento model);

    List<HistoricoAtendimentoResponseDTO> toHistoricoDTO (List<Atendimento> models);
}

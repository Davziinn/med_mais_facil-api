package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.encerrarAtendimento.EncerrarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.iniciarAtendimento.IniciarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.salvarAtendimento.SalvarAtendimentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.salvarAtendimento.SalvarAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.models.Atendimento;

public interface AtendimentoMapper {

    Atendimento toModel (AtendimentoEntity entity);

    Atendimento toModel (SalvarAtendimentoRequestDTO dto);

    AtendimentoEntity toEntity (Atendimento model);

    IniciarAtendimentoResponseDTO toIniciarDTO (Atendimento model);

    SalvarAtendimentoResponseDTO toSalvarDTO (Atendimento model);

    EncerrarAtendimentoResponseDTO toEncerrarDTO (Atendimento model);
}

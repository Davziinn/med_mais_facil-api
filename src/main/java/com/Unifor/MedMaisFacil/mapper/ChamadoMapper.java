package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.chamado.*;
import com.Unifor.MedMaisFacil.dtos.detalheChamado.DetalheChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.filaAtendimento.FilaAtendimentoResponseDTO;
import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.models.*;

import java.util.List;

public interface ChamadoMapper {

    Chamado toModel (ChamadoEntity entity);

    ChamadoEntity toEntity (Chamado model);

    Chamado toModel (ChamadoRequestDTO dto);

    Chamado toModel(ChamadoResponseDTO dto);

    ChamadoResponseDTO toDTO (Chamado model, List<ChamadoSintoma> chamadoSintomas);

    FilaAtendimentoResponseDTO toFilaAtendimentoDTO (Chamado model);

    DetalheChamadoResponseDTO toDetalheDTO (Chamado model, List<ChamadoSintoma> chamadoSintomas);

    List<SintomaDoChamado> toSintomas(ChamadoRequestDTO dto);
}

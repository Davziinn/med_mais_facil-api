package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoResponseDTO;
import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;
import com.Unifor.MedMaisFacil.models.Sintoma;

import java.util.List;

public interface ChamadoMapper {

    Chamado toModel (ChamadoEntity entity);

    ChamadoEntity toEntity (Chamado model);

    Chamado toModel (ChamadoRequestDTO dto);

    ChamadoResponseDTO toDTO (Chamado model, List<ChamadoSintoma> chamadoSintomas);

    List<Sintoma> toSintomas(ChamadoRequestDTO dto);
}

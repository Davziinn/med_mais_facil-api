package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.encaminharChamado.EncaminharChamadoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.encaminharChamado.EncaminharChamadoResponseDTO;
import com.Unifor.MedMaisFacil.models.EncaminharChamado;

public interface EncaminharChamadoMapper {

    EncaminharChamado toModel(EncaminharChamadoRequestDTO dto);

    EncaminharChamadoResponseDTO toDTO (EncaminharChamado model);
}

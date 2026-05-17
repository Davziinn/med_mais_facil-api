package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.encaminharChamado.EncaminharChamadoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.encaminharChamado.EncaminharChamadoResponseDTO;
import com.Unifor.MedMaisFacil.models.EncaminharChamado;
import com.Unifor.MedMaisFacil.models.EspecialidadeMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncaminharChamadoMapperImpl implements EncaminharChamadoMapper{

    @Autowired
    private EspecialidadeMapper especialidadeMapper;

    @Override
    public EncaminharChamado toModel(EncaminharChamadoRequestDTO dto) {
        EspecialidadeMedico especialidade =
                EspecialidadeMedico.builder()
                        .id(dto.especialidadeId())
                        .build();

        return EncaminharChamado.builder()
                .especialidadeMedico(especialidade)
                .build();
    }

    @Override
    public EncaminharChamadoResponseDTO toDTO(EncaminharChamado model) {
        return new EncaminharChamadoResponseDTO(
                model.getChamadoId(),
                model.getStatusChamado(),
                model.getEspecialidadeMedico() != null ? especialidadeMapper.toDTO(model.getEspecialidadeMedico()) : null
        );
    }
}

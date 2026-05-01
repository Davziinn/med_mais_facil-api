package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.medico.MedicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.medico.MedicoResponseDTO;
import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.models.Medico;
import com.Unifor.MedMaisFacil.utils.CalcularIdadeUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapperImpl implements MedicoMapper {

    @Override
    public Medico toModel(MedicoEntity entity) {
        return new Medico(
                entity.getId(),
                entity.getNome(),
                entity.getCrm(),
                entity.getEspecialidade(),
                entity.getSexo(),
                entity.getDataNascimento(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm()
        );
    }

    @Override
    public MedicoEntity toEntity(Medico model) {
        return new MedicoEntity(
                model.getId(),
                model.getNome(),
                model.getCrm(),
                model.getEspecialidade(),
                model.getSexo(),
                model.getDataNascimento(),
                model.getCriadoEm(),
                model.getAtualizadoEm()
        );
    }

    @Override
    public Medico toModel(MedicoRequestDTO dto) {
        return new Medico().toBuilder()
                .nome(dto.nome())
                .crm(dto.crm())
                .especialidade(dto.especialidade())
                .sexo(dto.sexo())
                .dataNascimento(dto.dataNascimento())
                .build();
    }

    @Override
    public MedicoResponseDTO toDTO(Medico model) {
        return new MedicoResponseDTO(
                model.getId(),
                model.getNome(),
                model.getCrm(),
                model.getEspecialidade(),
                model.getSexo(),
                CalcularIdadeUtils.calcular(model.getDataNascimento())
        );
    }
}

package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.models.Medico;
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
}

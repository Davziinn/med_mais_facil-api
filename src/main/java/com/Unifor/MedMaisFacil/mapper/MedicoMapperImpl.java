package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.medico.MedicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.medico.MedicoResponseDTO;
import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.models.Medico;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.utils.CalcularIdadeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapperImpl implements MedicoMapper {

    @Autowired
    private EspecialidadeMapper especialidadeMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public Medico toModel(MedicoEntity entity) {
        return new Medico(
                entity.getId(),
                entity.getCrm(),
                especialidadeMapper.toModel(entity.getEspecialidade()),
                entity.getSexo(),
                entity.getDataNascimento(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm(),
                entity.getUsuario() != null ? usuarioMapper.toModel(entity.getUsuario()) : null
        );
    }

    @Override
    public MedicoEntity toEntity(Medico model) {
        return new MedicoEntity(
                model.getId(),
                model.getCrm(),
                especialidadeMapper.toEntity(model.getEspecialidade()),
                model.getSexo(),
                model.getDataNascimento(),
                model.getCriadoEm(),
                model.getAtualizadoEm(),
                model.getUsuario() != null ? usuarioMapper.toEntity(model.getUsuario()) : null
        );
    }

    @Override
    public Medico toModel(MedicoRequestDTO dto) {
        return new Medico().toBuilder()
                .crm(dto.crm())
                .especialidade(especialidadeMapper.toModel(dto.especialidade()))
                .sexo(dto.sexo())
                .dataNascimento(dto.dataNascimento())
                .usuario(dto.usuarioId() != null
                        ? Usuario.builder().id(dto.usuarioId()).build()
                        : null)
                .build();
    }

    @Override
    public MedicoResponseDTO toDTO(Medico model) {
        return new MedicoResponseDTO(
                model.getId(),
                model.getUsuario().getNome(),
                model.getCrm(),
                especialidadeMapper.toDTO(model.getEspecialidade()),
                model.getSexo(),
                CalcularIdadeUtils.calcular(model.getDataNascimento())
        );
    }
}

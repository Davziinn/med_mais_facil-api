package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioRequestDTO;
import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioResponseDTO;
import com.Unifor.MedMaisFacil.entity.UsuarioEntity;
import com.Unifor.MedMaisFacil.models.Hospital;
import com.Unifor.MedMaisFacil.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImpl implements UsuarioMapper{

    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public Usuario toModel(UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .senhaHash(entity.getSenhaHash())
                .cpf(entity.getCpf())
                .tipoUsuario(entity.getTipoUsuario())
                .ativo(entity.getAtivo())
                .hospital(entity.getHospital() != null ? hospitalMapper.toModel(entity.getHospital()) : null)
                .criadoEm(entity.getCriadoEm())
                .atualizadoEm(entity.getAtualizadoEm())
                .build();
    }

    @Override
    public UsuarioEntity toEntity(Usuario model) {
        return UsuarioEntity.builder()
                .id(model.getId())
                .nome(model.getNome())
                .email(model.getEmail())
                .senhaHash(model.getSenhaHash())
                .cpf(model.getCpf())
                .tipoUsuario(model.getTipoUsuario())
                .ativo(model.getAtivo())
                .hospital(model.getHospital() != null ? hospitalMapper.toEntity(model.getHospital()) : null)
                .criadoEm(model.getCriadoEm())
                .atualizadoEm(model.getAtualizadoEm())
                .build();
    }

    @Override
    public Usuario toModel(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senhaHash(dto.senha())
                .cpf(dto.cpf())
                .tipoUsuario(dto.tipoUsuario())
                .hospital(dto.hospitalId() != null ? Hospital.builder().id(dto.hospitalId()).build() : null)
                .build();
    }

    @Override
    public UsuarioResponseDTO toDTO(Usuario model) {
        return new UsuarioResponseDTO(
                model.getId(),
                model.getNome(),
                model.getEmail(),
                model.getCpf(),
                model.getTipoUsuario(),
                model.getAtivo(),
                model.getHospital() != null ? hospitalMapper.toDTO(model.getHospital()) : null
        );
    }
}

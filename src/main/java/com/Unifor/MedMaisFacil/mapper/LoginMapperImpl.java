package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.auth.LoginRequestDTO;
import com.Unifor.MedMaisFacil.dtos.auth.LoginResponseDTO;
import com.Unifor.MedMaisFacil.models.Login;
import org.springframework.stereotype.Component;

@Component
public class LoginMapperImpl implements LoginMapper {
    @Override
    public Login toModel(LoginRequestDTO dto) {
        return Login.builder()
                .email(dto.email())
                .senhaHash(dto.senha())
                .build();
    }

    @Override
    public LoginResponseDTO toDTO(Login model) {
        return new LoginResponseDTO(
                model.getEmail(),
                model.getNome(),
                model.getToken(),
                model.getRole()
        );
    }
}

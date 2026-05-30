package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.auth.LoginRequestDTO;
import com.Unifor.MedMaisFacil.dtos.auth.LoginResponseDTO;
import com.Unifor.MedMaisFacil.models.Login;

public interface LoginMapper {

    Login toModel (LoginRequestDTO dto);

    LoginResponseDTO toDTO (Login model);
}

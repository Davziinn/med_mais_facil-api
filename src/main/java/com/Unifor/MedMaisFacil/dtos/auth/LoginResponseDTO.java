package com.Unifor.MedMaisFacil.dtos.auth;

public record LoginResponseDTO(
        String email,
        String nome,
        String token,
        String role,
        Long medicoId
) {
}

package com.Unifor.MedMaisFacil.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Login {
    private String email;
    private String senhaHash;
    private String nome;
    private String token;
    private String role;

    public Login (String email, String nome, String token, String role) {
        this.email = email;
        this.nome = nome;
        this.token = token;
        this.role = role;
    }
}

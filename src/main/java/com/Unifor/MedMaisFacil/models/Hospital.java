package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Hospital {

    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}

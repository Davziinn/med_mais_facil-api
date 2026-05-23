package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.StatusHospital;
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
    private String cidade;
    private String estado;
    private StatusHospital statusHospital;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}

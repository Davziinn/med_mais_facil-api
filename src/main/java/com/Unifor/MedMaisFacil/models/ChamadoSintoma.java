package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChamadoSintoma {

    private Long id;
    private Integer intensidade;
    private String descricaoLivre;
    private LocalDateTime dataRegistro;
    private Chamado chamado;
    private Sintoma sintoma;
}

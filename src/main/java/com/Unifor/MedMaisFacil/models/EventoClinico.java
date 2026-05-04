package com.Unifor.MedMaisFacil.models;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoClinico {

    private Long id;
    private String descricao;
}

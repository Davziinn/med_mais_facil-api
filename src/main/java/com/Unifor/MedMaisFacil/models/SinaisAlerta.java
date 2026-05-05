package com.Unifor.MedMaisFacil.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SinaisAlerta {

    private String descricao;
}

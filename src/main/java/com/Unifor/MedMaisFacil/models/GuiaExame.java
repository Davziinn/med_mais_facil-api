package com.Unifor.MedMaisFacil.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GuiaExame {

    private Long id;
    private GuiaMedica guia;
    private Exame exame;
}

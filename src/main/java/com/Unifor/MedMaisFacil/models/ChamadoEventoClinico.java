package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoEventoClinico {

    private Long id;
    private Chamado chamado;
    private EventoClinico eventoClinico;
    private LocalDateTime dataRegistro;
}

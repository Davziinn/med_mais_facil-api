package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class HospitalMedico {

    private Long id;
    private Hospital hospital;
    private Medico medico;
    private LocalDateTime criadoEm;
}

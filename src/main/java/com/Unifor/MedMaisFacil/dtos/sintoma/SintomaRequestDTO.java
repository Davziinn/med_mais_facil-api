package com.Unifor.MedMaisFacil.dtos.sintoma;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SintomaRequestDTO {

    @NotNull
    private Long id;
}

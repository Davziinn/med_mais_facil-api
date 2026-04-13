package com.Unifor.MedMaisFacil.dtos.sintomaChamado;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SintomaChamadoRequestDTO {

    @NotNull
    private Long id;

    @NotNull(message = "A intensidade é obrigatória")
    @Min(value = 1, message = "Intensidade mínima é 1")
    @Max(value = 10, message = "Intensidade máxima é 10")
    Integer intensidade;

    private String descricaoLivre;

}

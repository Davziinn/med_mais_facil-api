package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.StatusChamado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EncaminharChamado {

    private Long chamadoId;
    private StatusChamado statusChamado;
    private EspecialidadeMedico especialidadeMedico;

}

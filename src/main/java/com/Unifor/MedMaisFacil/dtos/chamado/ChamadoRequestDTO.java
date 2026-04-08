package com.Unifor.MedMaisFacil.dtos.chamado;

import com.Unifor.MedMaisFacil.dtos.chamadoSintoma.ChamadoSintomaRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record ChamadoRequestDTO(

        @NotBlank(message = "A descrição do relato é obrigatória")
        @Size(max = 1000, message = "A descrição não pode ultrapassar 1000 caracteres")
        String descricaoRelato,

        @NotNull(message = "O ID do paciente é obrigatório")
        Long pacienteId,

        @NotNull(message = "O ID do hospital é obrigatório")
        Long hospitalId,

        @NotEmpty(message = "Informe ao menos um sintoma")
        @Valid
        List<ChamadoSintomaRequestDTO> sintomas
) {
}

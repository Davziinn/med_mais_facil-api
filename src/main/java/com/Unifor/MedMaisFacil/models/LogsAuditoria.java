package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LogsAuditoria {

    private Long id;
    private Long usuarioId;
    private String nomeUsuario;
    private String acao;
    private String modulo;
    private String detalhes;
    private LocalDateTime criadoEm;
}

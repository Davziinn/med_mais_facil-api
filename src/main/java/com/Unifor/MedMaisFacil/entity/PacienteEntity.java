package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*;

@Entity
@Table(name = "TB_PAC")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLI")
    private Long id;

    @Column(name = "NM_CLI", nullable = false)
    private String nome;

    @Column(name = "CPF_CLI", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "DT_NASC_CLI", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "SX_CLI", nullable = false, length = 10)
    private String sexo;

    @Column(name = "DT_CRI_CLI")
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Column(name = "DT_ATZ_CLI")
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;
}

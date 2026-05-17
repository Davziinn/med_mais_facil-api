package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ESP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EspecialidadeMedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESP")
    private Long id;

    @Column(name = "NM_ESP", nullable = false, unique = true)
    private String nome;
}
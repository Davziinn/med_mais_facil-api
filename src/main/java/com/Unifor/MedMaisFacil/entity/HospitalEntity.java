package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_HOSP")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class HospitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HOSP")
    private Long id;

    @Column(name = "NM_HOSP", nullable = false)
    private String nome;

    @Column(name = "END_HOSP", nullable = false)
    private String endereco;

    @Column(name = "CNPJ_HOSP", nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(name = "DT_CRI_HOSP")
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Column(name = "DT_ATZ_HOSP")
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

}


package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*;

@Entity
@Table(name = "TB_MED")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MED")
    private Long id;

    @Column(name = "CRM_MED", nullable = false, unique = true)
    private String crm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESP")
    private EspecialidadeMedicoEntity especialidade;

    @Column(name = "SX_MED", nullable = false, length = 10)
    private String sexo;

    @Column(name = "DT_NASC_MED", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "DT_CRI_MED")
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Column(name = "DT_ATZ_MED")
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USU", nullable = false, unique = true)
    private UsuarioEntity usuario;
}

package com.Unifor.MedMaisFacil.entity;

import com.Unifor.MedMaisFacil.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*;

@Entity
@Table(name = "TB_CHAM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChamadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CHAM")
    private Long id;

    @Column(name = "DESC_RELA_CHAM", nullable = false, length = 500)
    private String descricaoRelato;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_CHAM", nullable = false)
    private StatusChamado statusChamado;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIO_CHAM", nullable = false)
    private PrioridadeChamado prioridadeChamado;

    @Column(name = "DT_HR_CHAM", nullable = false)
    private LocalDateTime dataHoraChamado;

    @CreationTimestamp
    @Column(name = "DT_CRI_CHAM")
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "DT_ATZ_CHAM")
    private LocalDateTime atualizadoEm;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalEntity hospital;
}

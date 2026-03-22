package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_HOSP_MED")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HospitalMedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HOSP_MED")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalEntity hospital;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private MedicoEntity medico;

    @CreationTimestamp
    @Column(name = "DT_CRI", updatable = false)
    private LocalDateTime criadoEm;
}

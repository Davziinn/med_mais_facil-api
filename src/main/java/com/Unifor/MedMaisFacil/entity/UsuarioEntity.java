package com.Unifor.MedMaisFacil.entity;

import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USU")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USU")
    private Long id;

    @Column(name = "NM_USU", nullable = false)
    private String nome;

    @Column(name = "EMAIL_USU", nullable = false, unique = true)
    private String email;

    @Column(name = "SNH_USU", nullable = false)
    private String senhaHash;

    @Column(name = "CPF_USU", nullable = false, unique = true, length = 14)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_USU", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "STS_USU", nullable = false)
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HOS", nullable = true)
    private HospitalEntity hospital;

    @Column(name = "DT_CRI_USU")
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Column(name = "DT_ATZ_USU")
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

}

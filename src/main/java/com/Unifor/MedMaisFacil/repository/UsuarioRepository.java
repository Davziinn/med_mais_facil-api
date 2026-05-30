package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.UsuarioEntity;
import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    List<UsuarioEntity> findByTipoUsuarioNot (TipoUsuario tipoUsuario);

    @Query("""
        SELECT COUNT(r)
        FROM UsuarioEntity r
        WHERE r.tipoUsuario = com.Unifor.MedMaisFacil.enums.TipoUsuario.RECEPCAO
        AND r.ativo = true
    """)
    long countRecepcaoAtivos ();

    @Query("""
        SELECT COUNT(r)
        FROM UsuarioEntity r
        WHERE r.tipoUsuario = com.Unifor.MedMaisFacil.enums.TipoUsuario.ADMINISTRADOR
        AND r.ativo = true
    """)
    long countAdmAtivos ();

    Optional<UsuarioEntity> findByEmail(String email);
}

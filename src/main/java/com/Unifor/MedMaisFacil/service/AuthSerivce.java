package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.UsuarioEntity;
import com.Unifor.MedMaisFacil.models.Login;
import com.Unifor.MedMaisFacil.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthSerivce {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final MedicoService medicoService;

    public Login realizarLogin(Login login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenhaHash())
        );

        String email = authentication.getName();
        UsuarioEntity usuario = (UsuarioEntity) usuarioService.loadUserByUsername(email);
        String token = jwtService.gerarToken(usuario);

        Long medicoId = medicoService.buscarMedicoIdByEmail(email);

        return new Login(
                usuario.getEmail(),
                usuario.getNome(),
                token,
                usuario.getTipoUsuario().name(),
                medicoId
        );
    }
}

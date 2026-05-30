package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.auth.LoginRequestDTO;
import com.Unifor.MedMaisFacil.dtos.auth.LoginResponseDTO;
import com.Unifor.MedMaisFacil.mapper.LoginMapper;
import com.Unifor.MedMaisFacil.models.Login;
import com.Unifor.MedMaisFacil.service.AuthSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthSerivce authSerivce;
    private final LoginMapper loginMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginRequestDTO dto) {
        try {
            Login loginRealizado = authSerivce.realizarLogin(loginMapper.toModel(dto));

            return ResponseEntity.ok(loginMapper.toDTO(loginRealizado));
        } catch (DisabledException e) {
            return ResponseEntity.status(403).build();
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        }
    }
}

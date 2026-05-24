package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioRequestDTO;
import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioResponseDTO;
import com.Unifor.MedMaisFacil.mapper.UsuarioMapper;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario (@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuarioMapper.toModel(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toDTO(usuarioSalvo));
    }
}

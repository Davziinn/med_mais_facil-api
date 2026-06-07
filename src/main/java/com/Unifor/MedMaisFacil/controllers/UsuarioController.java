package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioRequestDTO;
import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioResponseDTO;
import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioUpdateRequestDTO;
import com.Unifor.MedMaisFacil.mapper.UsuarioMapper;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Usuario usuario = usuarioMapper.toModel(dto);

        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toDTO(usuarioSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioById (@PathVariable Long id) {
        Usuario usuarioBuscado = usuarioService.buscarUsuarioById(id);

        return ResponseEntity.ok(usuarioMapper.toDTO(usuarioBuscado));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuariosNotPacientes () {
        List<Usuario> usuariosListados = usuarioService.listarUsuariosNotPacientes();

        return ResponseEntity.ok(usuariosListados.stream().map(usuarioMapper::toDTO).toList());
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario (@PathVariable Long id, @RequestBody UsuarioUpdateRequestDTO dto) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioMapper.toModel(dto));

        return ResponseEntity.ok(usuarioMapper.toDTO(usuarioAtualizado));
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletarUsuario (@PathVariable Long id) {
        usuarioService.deletarUsuarioById(id);

        return ResponseEntity.noContent().build();
    }
}

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
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuarioMapper.toModel(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toDTO(usuarioSalvo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios () {
        List<Usuario> usuariosListados = usuarioService.listarUsuarios();

        return ResponseEntity.ok(usuariosListados.stream().map(usuarioMapper::toDTO).toList());
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario (@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioMapper.toModel(dto));

        return ResponseEntity.ok(usuarioMapper.toDTO(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario (@PathVariable Long id) {
        usuarioService.deletarUsuarioById(id);

        return ResponseEntity.noContent().build();
    }
}

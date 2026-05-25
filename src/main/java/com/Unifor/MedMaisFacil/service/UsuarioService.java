package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.exceptions.UsuarioNotFoundException;
import com.Unifor.MedMaisFacil.mapper.UsuarioMapper;
import com.Unifor.MedMaisFacil.models.Hospital;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private HospitalService hospitalService;

    public Usuario salvarUsuario(Usuario usuario) {
        if (Objects.isNull(usuario)) return null;

        usuario = usuario.toBuilder()
                .ativo(true)
                .build();

        if (Objects.nonNull(usuario.getHospital()) && Objects.nonNull(usuario.getHospital().getId())) {
            Hospital hospitalEncontrado = hospitalService.buscarHospitalById(usuario.getHospital().getId());
            usuario = usuario.toBuilder()
                    .hospital(hospitalEncontrado)
                    .build();
        }

        return usuarioMapper.toModel(usuarioRepository.save(usuarioMapper.toEntity(usuario)));
    }

    public Usuario buscarUsuarioById (Long id){
        Usuario usuarioEncontrado = usuarioMapper.toModel(usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNotFoundException("Usuário não encontrado.")
        ));

        return usuarioEncontrado;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuariosEncontrados = usuarioRepository.findAll().stream().map(usuarioMapper::toModel).toList();

        return usuariosEncontrados;
    }

    public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
        Usuario usuarioEncontrado = buscarUsuarioById(id);

        Hospital hospitalEncontrado = hospitalService.buscarHospitalById(novoUsuario.getHospital().getId());

        usuarioEncontrado = usuarioEncontrado.toBuilder()
                .nome(novoUsuario.getNome())
                .email(novoUsuario.getEmail())
                .senhaHash(novoUsuario.getSenhaHash())
                .cpf(novoUsuario.getCpf())
                .telefone(novoUsuario.getTelefone())
                .tipoUsuario(usuarioEncontrado.getTipoUsuario())
                .ativo(novoUsuario.getAtivo())
                .hospital(hospitalEncontrado)
                .atualizadoEm(novoUsuario.getAtualizadoEm())
                .build();

        return usuarioMapper.toModel(usuarioRepository.save(usuarioMapper.toEntity(usuarioEncontrado)));
    }

    public void deletarUsuarioById(Long id) {
        usuarioRepository.deleteById(id);

    }
}

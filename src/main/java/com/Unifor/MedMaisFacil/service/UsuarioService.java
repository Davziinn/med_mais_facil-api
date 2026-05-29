package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import com.Unifor.MedMaisFacil.exceptions.UsuarioNotFoundException;
import com.Unifor.MedMaisFacil.mapper.UsuarioMapper;
import com.Unifor.MedMaisFacil.models.Hospital;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Auditable(acao = "Cadastrou um usuário", modulo = "Usuários")
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

    public List<Usuario> listarUsuariosNotPacientes() {
        List<Usuario> usuariosEncontrados = usuarioRepository.findByTipoUsuarioNot(TipoUsuario.PACIENTE).stream()
                .map(usuarioMapper::toModel)
                .toList();

        return usuariosEncontrados;
    }

    @Auditable(acao = "Atualizou um usuário", modulo = "Usuários")
    public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
        Usuario usuarioEncontrado = buscarUsuarioById(id);

        Hospital hospitalEncontrado = hospitalService.buscarHospitalById(novoUsuario.getHospital().getId());

        usuarioEncontrado = usuarioEncontrado.toBuilder()
                .nome(novoUsuario.getNome() != null ? novoUsuario.getNome() : usuarioEncontrado.getNome())
                .email(novoUsuario.getEmail() != null ? novoUsuario.getEmail() : usuarioEncontrado.getEmail())
                .senhaHash(novoUsuario.getSenhaHash() != null ? novoUsuario.getSenhaHash() : usuarioEncontrado.getSenhaHash())
                .cpf(novoUsuario.getCpf() != null ? novoUsuario.getCpf() : usuarioEncontrado.getCpf())
                .telefone(novoUsuario.getTelefone() != null ? novoUsuario.getTelefone() : usuarioEncontrado.getTelefone())
                .ativo(novoUsuario.getAtivo() != null ? novoUsuario.getAtivo() : usuarioEncontrado.getAtivo())
                .hospital(novoUsuario.getHospital() != null ? hospitalEncontrado : usuarioEncontrado.getHospital())
                .atualizadoEm(LocalDateTime.now())
                .build();

        return usuarioMapper.toModel(usuarioRepository.save(usuarioMapper.toEntity(usuarioEncontrado)));
    }

    @Auditable(acao = "Deletou um usuário", modulo = "Usuários")
    public void deletarUsuarioById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public long contarQuantidadeUsuariosRecepcaoAtivos () {
        return usuarioRepository.countRecepcaoAtivos();
    }

    public long contarQuantidadeUsuariosAdmAtivos () {
        return usuarioRepository.countAdmAtivos();
    }
}

package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.exceptions.UsuarioNotFoundException;
import com.Unifor.MedMaisFacil.mapper.UsuarioMapper;
import com.Unifor.MedMaisFacil.models.Hospital;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Hospital hospitalEncontrado = hospitalService.buscarHospitalById(usuario.getHospital().getId());

        usuario = usuario.toBuilder()
                .ativo(true)
                .hospital(hospitalEncontrado)
                .build();

        return usuarioMapper.toModel(usuarioRepository.save(usuarioMapper.toEntity(usuario)));
    }

    public Usuario buscarUsuarioById (Long id){
        Usuario usuarioEncontrado = usuarioMapper.toModel(usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNotFoundException("Usuário não encontrado.")
        ));

        return usuarioEncontrado;
    }
}

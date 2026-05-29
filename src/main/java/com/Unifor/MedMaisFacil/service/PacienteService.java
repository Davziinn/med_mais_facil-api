package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import com.Unifor.MedMaisFacil.exceptions.PacienteNotFoundException;
import com.Unifor.MedMaisFacil.mapper.PacienteMapper;
import com.Unifor.MedMaisFacil.models.Paciente;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private UsuarioService usuarioService;

    public Paciente salvarPaciente (Paciente paciente) {
        if (paciente == null) return null;

        Usuario usuarioSalvo = usuarioService.salvarUsuario(
                Usuario.builder()
                        .nome(paciente.getNome())
                        .cpf(paciente.getCpf())
                        .telefone(paciente.getTelefone())
                        .email(paciente.getEmail())
                        .senhaHash(paciente.getSenha())
                        .tipoUsuario(TipoUsuario.PACIENTE)
                        .ativo(true)
                        .build()
        );


        paciente = paciente.toBuilder()
                .usuario(usuarioSalvo)
                .build();

        return pacienteMapper.toModel(pacienteRepository.save(pacienteMapper.toEntity(paciente)));
    }

    public Paciente buscarPacienteById(Long id) {
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado"));

        return pacienteMapper.toModel(paciente);
    }

    public List<Paciente> buscarTodosPacientes () {
        List<PacienteEntity> pacientesBuscados = pacienteRepository.findAll();

        return pacientesBuscados.stream().map(pacienteMapper::toModel).toList();
    }

    public List<Paciente> buscarPacienteByNome (String nomePaciente) {
        return pacienteRepository.findByNomeContaining(nomePaciente).stream()
                .map(pacienteMapper::toModel)
                .toList();
    }

    public long contarQuantidadePacientes () {
        return pacienteRepository.countPacientes();
    }
}

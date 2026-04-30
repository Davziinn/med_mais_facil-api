package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.exceptions.PacienteNotFoundException;
import com.Unifor.MedMaisFacil.mapper.PacienteMapper;
import com.Unifor.MedMaisFacil.models.Paciente;
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

    public Paciente salvarPaciente (Paciente paciente) {
        if (paciente == null) return null;
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
}

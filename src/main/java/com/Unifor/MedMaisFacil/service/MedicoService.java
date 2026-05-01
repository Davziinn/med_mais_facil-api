package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.exceptions.MedicoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.MedicoMapper;
import com.Unifor.MedMaisFacil.models.Medico;
import com.Unifor.MedMaisFacil.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;


    public Medico salvarMedico (Medico medico) {
        if (medico == null) return null;
        return medicoMapper.toModel(medicoRepository.save(medicoMapper.toEntity(medico)));
    }

    public Medico consultarMedicoById (Long id) {
        Medico medicoEncontrado = medicoMapper.toModel(medicoRepository.findById(id).orElseThrow(
                () -> new MedicoNotFoundException("Médico não encontrado")
        ));

        return medicoEncontrado;
    }
}

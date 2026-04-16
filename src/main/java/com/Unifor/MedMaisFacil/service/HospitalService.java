package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.HospitalEntity;
import com.Unifor.MedMaisFacil.exceptions.HospitalNotFoundException;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private ChamadoMapper chamadoMapper;

    public Hospital salvarHospital (Hospital hospital) {
        if (hospital == null) return null;
        return hospitalMapper.toModel(hospitalRepository.save(hospitalMapper.toEntity(hospital)));
    }

    public Hospital buscarHospitalById(Long id) {
        HospitalEntity hospital = hospitalRepository.findById(id).orElseThrow(
                () -> new HospitalNotFoundException("Hospital não encontrado")
        );

        return hospitalMapper.toModel(hospital);
    }

    public List<Chamado> buscarFilaPorHospital(Long id) {
        return hospitalRepository.findFilaByHospital(id).stream()
                .map(chamadoMapper::toModel)
                .toList();
    }
}

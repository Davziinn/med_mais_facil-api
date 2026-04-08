package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.mapper.SintomaMapper;
import com.Unifor.MedMaisFacil.models.Sintoma;
import com.Unifor.MedMaisFacil.repository.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SintomaService {

    @Autowired
    private SintomaRepository sintomaRepository;

    @Autowired
    private SintomaMapper sintomaMapper;

    public Sintoma salvarSintoma (Sintoma sintoma) {
        if (sintoma == null) return null;
        return sintomaMapper.toModel(sintomaRepository.save(sintomaMapper.toEntity(sintoma)));
    }
}

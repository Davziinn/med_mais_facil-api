package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.mapper.EventoClinicoMapper;
import com.Unifor.MedMaisFacil.models.EventoClinico;
import com.Unifor.MedMaisFacil.repository.EventoClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoClinicoService {

    @Autowired
    private EventoClinicoRepository eventoClinicoRepository;

    @Autowired
    private EventoClinicoMapper eventoClinicoMapper;

    public EventoClinico salvarEventoClinico(EventoClinico eventoClinico) {
        if (eventoClinico == null) return null;
        return eventoClinicoMapper.toModel(eventoClinicoRepository.save(eventoClinicoMapper.toEntity(eventoClinico)));
    }

    public List<EventoClinico> buscarTodosEventosClinicos() {
        return eventoClinicoRepository.findAll().stream()
                .map(eventoClinicoMapper::toModel)
                .toList();
    }
}

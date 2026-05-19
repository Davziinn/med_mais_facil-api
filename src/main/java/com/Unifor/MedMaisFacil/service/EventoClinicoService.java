package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.exceptions.EventoClinicoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.EventoClinicoMapper;
import com.Unifor.MedMaisFacil.models.EventoClinico;
import com.Unifor.MedMaisFacil.repository.EventoClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public EventoClinico buscarEventoClinicoById (Long id) {
        if (Objects.isNull(id)) return null;

        return eventoClinicoMapper.toModel(eventoClinicoRepository.findById(id).orElseThrow(
                () -> new EventoClinicoNotFoundException("Evento não encontrado")
        ));
    }

    public EventoClinico atualizarEventoClinico(Long id, EventoClinico eventoClinico) {
        if (Objects.isNull(eventoClinico)) return null;

        EventoClinico eventoClinicoEncontrado = buscarEventoClinicoById(id);

        eventoClinicoEncontrado = eventoClinicoEncontrado.toBuilder()
                .nomeEvento(eventoClinico.getNomeEvento())
                .descricao(eventoClinico.getDescricao())
                .severidade(eventoClinico.getSeveridade())
                .build();

        return salvarEventoClinico(eventoClinicoEncontrado);
    }

    public void deletarEventoClinicoById (Long id) {
        eventoClinicoRepository.deleteById(id);
    }
}

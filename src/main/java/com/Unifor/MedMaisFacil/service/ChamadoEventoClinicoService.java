package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.mapper.ChamadoEventoClinicoMapper;
import com.Unifor.MedMaisFacil.mapper.EventoClinicoMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.ChamadoEventoClinico;
import com.Unifor.MedMaisFacil.models.EventoClinico;
import com.Unifor.MedMaisFacil.repository.ChamadoEventoClinicoRepository;
import com.Unifor.MedMaisFacil.repository.EventoClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChamadoEventoClinicoService {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private ChamadoEventoClinicoRepository chamadoEventoClinicoRepository;

    @Autowired
    private ChamadoEventoClinicoMapper chamadoEventoClinicoMapper;

    @Autowired
    private EventoClinicoMapper eventoClinicoMapper;

    @Autowired
    private EventoClinicoRepository eventoClinicoRepository;

    @Transactional
    public void salvarEventosClinicosByChamado (Long chamadoId, List<Long> eventosIds) {
        if (eventosIds == null) {
            throw new RuntimeException("Lista de eventos não pode ser nula");
        }

        Chamado chamadoEncontrado = chamadoService.consultarDetalhesChamado(chamadoId);

        if (!chamadoEncontrado.getStatusChamado().equals(StatusChamado.EM_ESPERA)) {
            throw new RuntimeException("Não é possível alterar eventos neste estado");
        }

        chamadoEventoClinicoRepository.deleteByChamadoId(chamadoId);

        List<EventoClinico> todosEventosByEventoIdEncontrados = eventoClinicoRepository
                .findAllById(eventosIds)
                .stream()
                .map(eventoClinicoMapper::toModel)
                .toList();

        if (todosEventosByEventoIdEncontrados.size() != eventosIds.size()) {
            throw new RuntimeException("Um ou mais eventos clínicos não existem");
        }

        List<ChamadoEventoClinico> chamadoEventoClinicos = todosEventosByEventoIdEncontrados.stream()
                .map(eventoClinico -> ChamadoEventoClinico.builder()
                        .chamado(chamadoEncontrado)
                        .eventoClinico(eventoClinico)
                        .build())
                .toList();

        chamadoEventoClinicoRepository.saveAll(chamadoEventoClinicos.stream()
                .map(chamadoEventoClinicoMapper::toEntity)
                .toList());
    }
}

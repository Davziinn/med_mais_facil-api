package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.eventoClinico.EventoClinicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.eventoClinico.EventoClinicoResponseDTO;
import com.Unifor.MedMaisFacil.mapper.EventoClinicoMapper;
import com.Unifor.MedMaisFacil.models.EventoClinico;
import com.Unifor.MedMaisFacil.service.EventoClinicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/v1/evento")
public class EventoClinicoController {

    @Autowired
    private EventoClinicoService eventoClinicoService;

    @Autowired
    private EventoClinicoMapper eventoClinicoMapper;

    @PostMapping
    public ResponseEntity<EventoClinicoResponseDTO> cadastrarEventoClinico (@RequestBody EventoClinicoRequestDTO eventoClinico) {
        EventoClinico eventoClinicoSalvo = eventoClinicoService.salvarEventoClinico(eventoClinicoMapper.toModel(eventoClinico));

        return ResponseEntity.status(HttpStatus.CREATED).body(eventoClinicoMapper.toDTO(eventoClinicoSalvo));
    }

    @GetMapping
    public ResponseEntity<List<EventoClinicoResponseDTO>> listaTodosEventosClinicos () {
        List<EventoClinico> eventosClinicosEncontrados = eventoClinicoService.buscarTodosEventosClinicos();

        return ResponseEntity.ok(eventosClinicosEncontrados.stream().map(eventoClinicoMapper::toDTO).toList());
    }
}

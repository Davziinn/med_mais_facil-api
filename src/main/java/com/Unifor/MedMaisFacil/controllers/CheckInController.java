package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.checkin.ConfirmarCheckinResponseDTO;
import com.Unifor.MedMaisFacil.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/checkin")
public class CheckInController {

    @Autowired
    private ChamadoService chamadoService;

    @PutMapping("/{chamadoId}/confirmar")
    public ResponseEntity<Void> confirmarCheckin (@PathVariable Long chamadoId) {
        chamadoService.confirmarCheckin(chamadoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{chamadoId}/cancelar")
    public ResponseEntity<Void> cancelarChamado (@PathVariable Long chamadoId) {
        chamadoService.cancelarChamado(chamadoId);

        return ResponseEntity.noContent().build();
    }
}

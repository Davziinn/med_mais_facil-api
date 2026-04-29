package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TriagemService {

    @Autowired
    private PacienteService pacienteService;

    public PrioridadeChamado calcularPrioridade (Chamado chamado) {
        int idadePaciente = calcularIdade(chamado.getPaciente());

        if (idadePaciente >= 60) {
            return chamado.definirPrioridade(PrioridadeChamado.CRITICA);
        } else if (idadePaciente >= 18) {
            return chamado.definirPrioridade(PrioridadeChamado.MEDIA);
        } else {
            return chamado.definirPrioridade(PrioridadeChamado.ALTA);
        }
    }

    public int calcularIdade (Paciente paciente) {

        Paciente pacienteEncontrato = pacienteService.buscarPacienteById(paciente.getId());

        return Period.between(
                pacienteEncontrato.getDataNascimento(),
                LocalDate.now()
        ).getYears();
    }
}

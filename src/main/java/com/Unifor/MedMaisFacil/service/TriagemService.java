package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.Paciente;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class TriagemService {

    public void calcularPrioridade (Chamado chamado) {
        int idadePaciente = calcularIdade(chamado.getPaciente());

        if (idadePaciente >= 60) {
            chamado.definirPrioridade(PrioridadeChamado.CRITICA);
        } else if (idadePaciente >= 18) {
            chamado.definirPrioridade(PrioridadeChamado.MEDIA);
        } else {
            chamado.definirPrioridade(PrioridadeChamado.ALTA);
        }
    }

    private int calcularIdade (Paciente paciente) {
        return Period.between(
                paciente.getDataNascimento(),
                LocalDate.now()
        ).getYears();
    }
}

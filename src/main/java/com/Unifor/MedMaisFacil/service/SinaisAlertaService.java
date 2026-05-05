package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.models.ChamadoEventoClinico;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;
import com.Unifor.MedMaisFacil.models.SinaisAlerta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SinaisAlertaService {

    public List<SinaisAlerta> gerarSinaisAlerta (List<ChamadoEventoClinico> chamadoEventoClinicos, List<ChamadoSintoma> chamadoSintomas) {
        List<SinaisAlerta> sinaisAlertas = new ArrayList<>();

        chamadoSintomas.stream()
                .filter(sintoma -> sintoma.getIntensidade() >= 8)
                .forEach(sintoma -> sinaisAlertas.add(
                        new SinaisAlerta("Sintoma Intenso: " + sintoma.getSintoma().getDescricao())
                ));

        chamadoEventoClinicos.stream()
                .filter(evento -> {
                    String desc = evento.getEventoClinico().getDescricao();
                    return desc.startsWith("Crise") || desc.startsWith("Acidente") || desc.startsWith("Desmaio") || desc.startsWith("Sangr") || desc.startsWith("Tontura") || desc.startsWith("Cirurgia");
                })
                .forEach(sintoma -> sinaisAlertas.add(
                        new SinaisAlerta("Ficar de olho")
                ));


        return sinaisAlertas;
    }

}

package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.Severidade;
import com.Unifor.MedMaisFacil.models.ChamadoEventoClinico;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;
import com.Unifor.MedMaisFacil.models.SinaisAlerta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SinaisAlertaService {

    public List<SinaisAlerta> gerarSinaisAlerta(
            List<ChamadoEventoClinico> chamadoEventoClinicos,
            List<ChamadoSintoma> chamadoSintomas) {

        List<SinaisAlerta> sinaisAlertas = new ArrayList<>();

        chamadoSintomas.stream()
                .filter(sintoma -> sintoma.getIntensidade() >= 8)
                .forEach(sintoma -> sinaisAlertas.add(
                        new SinaisAlerta(
                                "Sintoma intenso: " + sintoma.getSintoma().getDescricao(),
                                Severidade.CRITICO
                        )
                ));

        chamadoEventoClinicos.stream()
                .filter(evento -> evento.getEventoClinico().getSeveridade() == Severidade.CRITICO
                        || evento.getEventoClinico().getSeveridade() == Severidade.MODERADO)
                .forEach(evento -> sinaisAlertas.add(
                        new SinaisAlerta(
                                evento.getEventoClinico().getDescricao(),
                                evento.getEventoClinico().getSeveridade()
                        )
                ));

        return sinaisAlertas.stream()
                .sorted(Comparator.comparing(SinaisAlerta::getSeveridade).reversed())
                .limit(3)
                .toList();
    }

}

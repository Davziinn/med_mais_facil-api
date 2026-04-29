package com.Unifor.MedMaisFacil.utils;

import java.time.LocalDate;
import java.time.Period;

public class CalcularIdadeUtils {
    public static int calcular(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}

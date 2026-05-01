package com.Unifor.MedMaisFacil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponse (HttpStatus httpStatus, String message) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", httpStatus.value());
        body.put("error", httpStatus.getReasonPhrase());
        body.put("messagem", message);

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<Object> handlePacienteNotFoundException(PacienteNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(SintomaNotFoundException.class)
    public ResponseEntity<Object> handleSintomaNotFoundException (SintomaNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(HospitalNotFoundException.class)
    public ResponseEntity<Object> handleHospitalNotFoundException (HospitalNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ChamadoNotFoundException.class)
    public ResponseEntity<Object> handleChamadoNotFoundException (ChamadoNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity<Object> handleMedicoNotFoundException (MedicoNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ChamadoNotAvailableException.class)
    public ResponseEntity<Object> handleChamadoNotAvailableException (ChamadoNotAvailableException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}

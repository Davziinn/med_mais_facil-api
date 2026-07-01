package com.Unifor.MedMaisFacil.exceptions.globalException;

import com.Unifor.MedMaisFacil.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AtendimentoNotFoundException.class)
    public ResponseEntity<Object> handleAtendimentoNotFoundException (AtendimentoNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EspecialidadeMedicoNotFoundException.class)
    public ResponseEntity<Object> handleEspecialidadeMedicoNotFoundException (EspecialidadeMedicoNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EventoClinicoNotFoundException.class)
    public ResponseEntity<Object> handleEventoClinicoNotFoundException (EventoClinicoNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Object> handleUsuarioNotFoundException (UsuarioNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ExameNotFoundException.class)
    public ResponseEntity<Object> handleExameNotFoundException (ExameNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(GuiaMedicaNotFoundException.class)
    public ResponseEntity<Object> handleGuiaMedicaNotFoundException (GuiaMedicaNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException (Exception ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException (DataIntegrityViolationException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException (RegraNegocioException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ErrorGerarGuiaMedicaException.class)
    public ResponseEntity<Object> handleErrorGerarGuiaMedicaException (ErrorGerarGuiaMedicaException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ErrorConsultarGuiaMedica.class)
    public ResponseEntity<Object> handleErrorConsultarGuiaMedica (ErrorConsultarGuiaMedica ex){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "error", "Acesso negado",
                        "message", "Você não tem permissão para acessar este recurso"
                ));
    }
}

package com.Unifor.MedMaisFacil.aspect;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.models.LogsAuditoria;
import com.Unifor.MedMaisFacil.service.LogsAuditoriaService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final LogsAuditoriaService logsAuditoriaService;

    @AfterReturning("@annotation(auditable)")
    public void registrarLog(JoinPoint joinPoint, Auditable auditable) {

            /*PARA QUANDO TIVER AUTENTICAÇÃO*/
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        String nomeUsuario = "Sistema";
//        if (authentication != null && authentication.isAuthenticated()) {
//            nomeUsuario = authentication.getName();
//        }
//
//        LogsAuditoria log = LogsAuditoria.builder()
//                .nomeUsuario(nomeUsuario)
//                .acao(auditable.acao())
//                .modulo(auditable.modulo())
//                .build();
//
//        logsAuditoriaService.registrarLog(log);

        LogsAuditoria logsAuditoriaRegistrado = LogsAuditoria.builder()
                .nomeUsuario("Sistema")
                .acao(auditable.acao())
                .modulo(auditable.modulo())
                .build();

        logsAuditoriaService.registrarLog(logsAuditoriaRegistrado);
    }
}

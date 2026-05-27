package com.Unifor.MedMaisFacil.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {
    String acao();
    String modulo();
}

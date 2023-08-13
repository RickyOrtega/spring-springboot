package com.bolsadeideas.springboot.form.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.thymeleaf.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty() || value.isBlank()){
            return false;
        }
        return true;
    }
}
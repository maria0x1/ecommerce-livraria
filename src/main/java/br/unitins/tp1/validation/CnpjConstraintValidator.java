package br.unitins.tp1.validation;

import br.unitins.tp1.model.CnpjUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjConstraintValidator implements ConstraintValidator<Cnpj, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || CnpjUtils.isValid(value);
    }
}

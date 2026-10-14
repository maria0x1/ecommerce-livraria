package br.unitins.tp1.validation;

import br.unitins.tp1.model.IsbnUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnConstraintValidator implements ConstraintValidator<Isbn, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || IsbnUtils.isValid(value);
    }
}

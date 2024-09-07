package dev.mayank.infinityschoolhouse.validations;

import dev.mayank.infinityschoolhouse.annotations.FieldValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldValueMatchValidator implements ConstraintValidator<FieldValueMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldValueMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.firstField();
        secondFieldName = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        Object field1Value = new BeanWrapperImpl(object).getPropertyValue(firstFieldName);
        Object field2Value = new BeanWrapperImpl(object).getPropertyValue(secondFieldName);

        if (field1Value != null)
            return field1Value.equals(field2Value);

        return field2Value == null;
    }
}
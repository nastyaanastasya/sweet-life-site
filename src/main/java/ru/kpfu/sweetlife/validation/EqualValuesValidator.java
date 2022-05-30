package ru.kpfu.sweetlife.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualValuesValidator implements ConstraintValidator<EqualValues, Object> {

    private String firstValue;
    private String secondValue;

    @Override
    public void initialize(EqualValues constraintAnnotation) {
        firstValue = constraintAnnotation.firstValue();
        secondValue = constraintAnnotation.secondValue();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl bean = new BeanWrapperImpl(o);
        Object first = bean.getPropertyValue(firstValue);
        Object second = bean.getPropertyValue(secondValue);
        return first != null && first.equals(second);
    }
}

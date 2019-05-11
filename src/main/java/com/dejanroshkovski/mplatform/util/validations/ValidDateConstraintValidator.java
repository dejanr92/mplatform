package com.dejanroshkovski.mplatform.util.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateConstraintValidator implements ConstraintValidator<ValidDateValidation, String> {

    private String dateFormat;

    @Override
    public void initialize(ValidDateValidation validDate) {
        // startsWith is the passed value from the Annotation
        dateFormat = validDate.value();
    }

    @Override
    public boolean isValid(String enteredText, ConstraintValidatorContext context) {

        if(enteredText != null){
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            try{
                format.parse(enteredText);
                return true;
            } catch(ParseException e){
                return false;
            }
        } else{
            return true;
        }
    }
    
}
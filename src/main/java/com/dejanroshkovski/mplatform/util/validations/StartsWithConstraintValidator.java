package com.dejanroshkovski.mplatform.util.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartsWithConstraintValidator implements ConstraintValidator<StartsWithValidation, String> {

    // TODO Depreciated, this is currently not in use, however it is placed as a showcase. 
    // The current implementation is using regex

    private String prefix;

    // Takes the arguments from the @StartsWith(value="STUFF", message="STUFF") Annotation
    @Override
    public void initialize(StartsWithValidation startsWith) {
        // startsWith is the passed value from the Annotation
        prefix = startsWith.value();
    }

    @Override
    public boolean isValid(String enteredText, ConstraintValidatorContext context) {

        boolean result;
        System.out.println(enteredText);

        if(enteredText != null){
            result = enteredText.startsWith(prefix);
        } else{
            result = true;
        }
        return result;
    }
    
}
package com.dejanroshkovski.mplatform.util.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = StartsWithConstraintValidator.class) // Validation Class
@Target({ ElementType.METHOD, ElementType.FIELD }) // Apply on both Method and field
@Retention(RetentionPolicy.RUNTIME) // Retain at runtime

// Define the annotation type for validation
public @interface StartsWithValidation{

    // TODO Depreciated, this is currently not in use, however it is placed as a showcase. 
    // The current implementation is using regex

    public String value() default "DN";

    public String message() default "Must Start with DN";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
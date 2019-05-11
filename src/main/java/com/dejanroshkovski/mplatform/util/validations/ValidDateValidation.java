package com.dejanroshkovski.mplatform.util.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidDateConstraintValidator.class) // Validation Class
@Target({ ElementType.METHOD, ElementType.FIELD }) // Apply on both Method and field
@Retention(RetentionPolicy.RUNTIME) // Retain at runtime

// Define the annotation type for validation
public @interface ValidDateValidation{

    public String value() default "yyyy-MM-dd";

    public String message() default "Must be valid date with yyyy-MM-dd format";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
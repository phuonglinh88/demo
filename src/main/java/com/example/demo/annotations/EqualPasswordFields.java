package com.example.demo.annotations;

import com.example.demo.validator.EqualFieldsValidator;
import com.example.demo.validator.EqualPasswordFieldsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Linh
 * @project demo
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualPasswordFieldsValidator.class)
@Documented
public @interface EqualPasswordFields {
    
    String message() default "Incorrect Password";
    Class<?>[] groups() default {};
    Class<? extends Payload >[] payload() default {};
    String baseField();
    String matchField();
}

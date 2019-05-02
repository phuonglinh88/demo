package com.example.demo.validator;

import com.example.demo.annotations.EqualPasswordFields;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * @author Linh
 * @project demo
 */
public class EqualPasswordFieldsValidator implements ConstraintValidator< EqualPasswordFields, Object > {
    
    private String baseField;
    private String matchField;
    private String message;
    @Autowired
    private UserService userService;
    
    @Override
    public void initialize(EqualPasswordFields constraint) {
        baseField = constraint.baseField();
        matchField = constraint.matchField();
        message = constraint.message();
    }
    
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            Object baseFieldValue = getFieldValue(object, baseField);
            Object matchFieldValue = getFieldValue(object, matchField);
            
            valid = baseFieldValue != null && userService.checkUserByIdAndPassword((Long) baseFieldValue, matchFieldValue.toString());
        } catch (Exception ignored) {
        }
        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(matchField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        
        return valid;
    }
    
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class< ? > clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }
    
}

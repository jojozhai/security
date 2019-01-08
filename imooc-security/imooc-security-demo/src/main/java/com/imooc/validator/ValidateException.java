/**
 *
 */
package com.imooc.validator;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author zhailiang
 *
 */
public class ValidateException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7207451175263593487L;

    private List<ObjectError> errors;

    public ValidateException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }

}

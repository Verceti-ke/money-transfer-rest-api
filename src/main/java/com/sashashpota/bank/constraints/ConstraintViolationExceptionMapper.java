package com.sashashpota.bank.constraints;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.ext.Provider;

import static java.util.stream.Collectors.toList;

@Provider
public class ConstraintViolationExceptionMapper extends BadRequestExceptionMapper<ConstraintViolationException> {
    @Override
    public Object getResponseEntity(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(toList());
    }
}

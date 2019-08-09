package com.sashashpota.bank.constraints;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ConstraintViolationExceptionMapperTest {

    @Test
    public void shouldGetResponseEntity() {
        ConstraintViolationExceptionMapper mapper = new ConstraintViolationExceptionMapper();
        ConstraintViolation violation = mock(ConstraintViolation.class);
        given(violation.getMessage()).willReturn("violation message");
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        given(exception.getConstraintViolations()).willReturn(singleton(violation));

        Object entity = mapper.getResponseEntity(exception);

        assertEquals(singletonList("violation message"), entity);
    }
}
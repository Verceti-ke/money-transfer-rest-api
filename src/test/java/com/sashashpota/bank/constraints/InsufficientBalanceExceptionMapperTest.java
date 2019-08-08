package com.sashashpota.bank.constraints;

import com.sashashpota.bank.exceptions.InsufficientBalanceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsufficientBalanceExceptionMapperTest {

    @Test
    public void shouldGetResponseEntity() {
        InsufficientBalanceExceptionMapper mapper = new InsufficientBalanceExceptionMapper();
        InsufficientBalanceException exception = new InsufficientBalanceException();

        Object entity = mapper.getResponseEntity(exception);

        assertEquals("Insufficient balance", entity);
    }
}
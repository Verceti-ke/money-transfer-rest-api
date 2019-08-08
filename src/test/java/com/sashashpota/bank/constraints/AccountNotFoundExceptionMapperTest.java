package com.sashashpota.bank.constraints;

import com.sashashpota.bank.exceptions.AccountNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountNotFoundExceptionMapperTest {

    @Test
    public void shouldGetResponseEntity() {
        AccountNotFoundExceptionMapper mapper = new AccountNotFoundExceptionMapper();
        AccountNotFoundException ex = new AccountNotFoundException("Account is not found");

        Object responseEntity = mapper.getResponseEntity(ex);

        assertEquals(responseEntity, "Account is not found");
    }
}
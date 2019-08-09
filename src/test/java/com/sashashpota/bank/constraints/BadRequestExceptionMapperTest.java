package com.sashashpota.bank.constraints;

import com.sashashpota.bank.exceptions.InsufficientBalanceException;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.junit.Assert.assertEquals;

public class BadRequestExceptionMapperTest {

    @Test
    public void shouldConvertToResponse() {
        BadRequestExceptionMapper<InsufficientBalanceException> mapper = new InsufficientBalanceExceptionMapper();
        InsufficientBalanceException exception = new InsufficientBalanceException();

        Response response = mapper.toResponse(exception);

        assertEquals(400, response.getStatus());
        assertEquals(APPLICATION_JSON_TYPE, response.getMediaType());
        assertEquals("Insufficient balance", response.getEntity());
    }
}

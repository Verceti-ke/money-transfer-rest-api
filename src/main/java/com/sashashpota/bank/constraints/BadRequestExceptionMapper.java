package com.sashashpota.bank.constraints;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.status;

public abstract class BadRequestExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {
    @Override
    public Response toResponse(E exception) {
        Object entity = getResponseEntity(exception);
        return status(BAD_REQUEST)
                .type(APPLICATION_JSON_TYPE)
                .entity(entity)
                .build();
    }

    abstract Object getResponseEntity(E exception);
}

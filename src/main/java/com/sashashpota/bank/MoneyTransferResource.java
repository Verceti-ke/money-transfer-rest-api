package com.sashashpota.bank;

import org.jboss.shrinkwrap.impl.base.Validate;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Singleton
@Path("/transfer")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MoneyTransferResource {
    private final MoneyTransferService service;

    @Inject
    public MoneyTransferResource(@Context MoneyTransferService service) {
        Validate.notNull(service, "service must not be null");
        this.service = service;
    }

    @POST
    public void transfer(@Valid @NotNull(message = "request body must not be null") Transfer tr) {
        service.transfer(tr.getFromId(), tr.getToId(), tr.getAmount());
    }
}
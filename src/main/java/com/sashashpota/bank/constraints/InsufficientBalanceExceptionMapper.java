package com.sashashpota.bank.constraints;

import com.sashashpota.bank.exceptions.InsufficientBalanceException;

import javax.ws.rs.ext.Provider;

@Provider
public class InsufficientBalanceExceptionMapper extends BadRequestExceptionMapper<InsufficientBalanceException> {
    @Override
    Object getResponseEntity(InsufficientBalanceException exception) {
        return "Insufficient balance";
    }
}
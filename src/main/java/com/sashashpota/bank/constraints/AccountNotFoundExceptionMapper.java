package com.sashashpota.bank.constraints;

import com.sashashpota.bank.exceptions.AccountNotFoundException;

import javax.ws.rs.ext.Provider;

@Provider
public class AccountNotFoundExceptionMapper extends BadRequestExceptionMapper<AccountNotFoundException> {
    @Override
    Object getResponseEntity(AccountNotFoundException exception) {
        return exception.getMessage();
    }
}
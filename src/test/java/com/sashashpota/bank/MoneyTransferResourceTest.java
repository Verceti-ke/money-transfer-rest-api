package com.sashashpota.bank;

import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MoneyTransferResourceTest {

    @Test
    public void shouldTransfer() {
        MoneyTransferService service = mock(MoneyTransferService.class);
        MoneyTransferResource resource = new MoneyTransferResource(service);

        resource.transfer(new Transfer("1", "2", new BigDecimal("11.22")));

        verify(service).transfer("1", "2", new BigDecimal("11.22"));
    }
}
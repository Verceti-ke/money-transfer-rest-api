package com.sashashpota.bank;

import com.sashashpota.bank.exceptions.AccountNotFoundException;
import com.sashashpota.bank.exceptions.InsufficientBalanceException;
import com.sashashpota.bank.persistance.PersistenceHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MoneyTransferServiceTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldTransfer() {
        PersistenceHelper helper = mock(PersistenceHelper.class);
        EntityManager em = mockEntityManager(helper);
        MoneyTransferService service = new MoneyTransferService(helper);

        service.transfer("1", "2", new BigDecimal("10.11"));

        verify(em).persist(account("1", "1.11"));
        verify(em).persist(account("2", "11.33"));
    }

    @Test
    public void shouldFailToTransferGivenFromIsNull() {
        expectedEx.expect(AccountNotFoundException.class);
        expectedEx.expectMessage("Account not found, id: 1");
        PersistenceHelper helper = mock(PersistenceHelper.class);
        MoneyTransferService service = new MoneyTransferService(helper);
        EntityManager em = assignEntityManager(helper);
        given(em.find(Account.class, "2", PESSIMISTIC_WRITE))
                .willReturn(account("2", "1.22"));

        service.transfer("1", "2", new BigDecimal("10.11"));
    }

    @Test
    public void shouldFailToTransferGivenToIsNull() {
        expectedEx.expect(AccountNotFoundException.class);
        expectedEx.expectMessage("Account not found, id: 2");
        PersistenceHelper helper = mock(PersistenceHelper.class);
        MoneyTransferService service = new MoneyTransferService(helper);
        EntityManager em = assignEntityManager(helper);
        given(em.find(Account.class, "1", PESSIMISTIC_WRITE))
                .willReturn(account("1", "1.22"));

        service.transfer("1", "2", new BigDecimal("10.11"));
    }

    @Test
    public void shouldFailToTransferGivenInsufficientBalance() {
        expectedEx.expect(InsufficientBalanceException.class);
        PersistenceHelper helper = mock(PersistenceHelper.class);
        MoneyTransferService service = new MoneyTransferService(helper);
        mockEntityManager(helper);

        service.transfer("1", "2", new BigDecimal("100.00"));
    }

    private Account account(String id, String amount) {
        return new Account(id, new BigDecimal(amount));
    }

    private EntityManager assignEntityManager(PersistenceHelper helper) {
        EntityManager em = mock(EntityManager.class);
        given(helper.getEntityManager()).willReturn(em);
        return em;
    }

    private EntityManager mockEntityManager(PersistenceHelper helper) {
        EntityManager em = assignEntityManager(helper);
        Account from = account("1", "11.22");
        Account to = account("2", "1.22");
        given(em.find(Account.class, "1", PESSIMISTIC_WRITE)).willReturn(from);
        given(em.find(Account.class, "2", PESSIMISTIC_WRITE)).willReturn(to);
        return em;
    }
}
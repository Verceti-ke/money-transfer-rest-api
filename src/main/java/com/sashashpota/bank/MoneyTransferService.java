package com.sashashpota.bank;

import com.sashashpota.bank.exceptions.AccountNotFoundException;
import com.sashashpota.bank.exceptions.InsufficientBalanceException;
import com.sashashpota.bank.persistance.PersistenceHelper;
import org.jboss.shrinkwrap.impl.base.Validate;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import java.math.BigDecimal;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Model
@Singleton
class MoneyTransferService {
    private final PersistenceHelper persistenceHelper;

    @Inject
    MoneyTransferService(@Context PersistenceHelper persistenceHelper) {
        Validate.notNull(persistenceHelper, "persistenceHelper must not be null");
        this.persistenceHelper = persistenceHelper;
    }

    @Transactional
    void transfer(String fromId, String toId, BigDecimal amount) {
        EntityManager entityManager = persistenceHelper.getEntityManager();
        Account from = getAccount(entityManager, fromId);
        Account to = getAccount(entityManager, toId);
        BigDecimal fromAmount = from.getAmount();
        if (amount.compareTo(fromAmount) > 0) {
            throw new InsufficientBalanceException();
        }
        from.setAmount(fromAmount.subtract(amount));
        BigDecimal toAmount = to.getAmount();
        to.setAmount(toAmount.add(amount));
        entityManager.persist(from);
        entityManager.persist(to);
    }

    private Account getAccount(EntityManager entityManager, String id) {
        Account account = entityManager.find(Account.class, id, PESSIMISTIC_WRITE);
        if (account == null) {
            throw new AccountNotFoundException("Account not found, id: " + id);
        }
        return  account;
    }
}

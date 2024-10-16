package dev.alejandro.transactionalDecorator.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Account(UUID accountId, UUID clientId, BigDecimal balance) {

    public boolean hasSufficientBalance(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    public Account deposit(BigDecimal amount) {
        var newBalance = balance.add(amount);
        return new Account(accountId, clientId, newBalance);
    }

    public Account withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            var newBalance = balance.subtract(amount);
            return new Account(accountId, clientId, newBalance);
        }
        return this;
    }
}

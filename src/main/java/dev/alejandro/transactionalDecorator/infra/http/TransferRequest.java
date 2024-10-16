package dev.alejandro.transactionalDecorator.infra.http;

import java.io.Serializable;
import java.math.BigDecimal;

public record TransferRequest(
        String accountIdFrom,
        String accountIdTo,
        BigDecimal amount
) implements Serializable {
    public boolean isInvalid() {
        return accountIdFrom == null || accountIdFrom.isBlank() || accountIdTo == null || accountIdTo.isBlank() || amount() == null || amount.compareTo(BigDecimal.ZERO) <= 0;
    }
}

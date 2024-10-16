package dev.alejandro.transactionalDecorator.application;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransferService {
    void transfer(UUID accountIdFrom, UUID accountIdTo, BigDecimal amount) throws TransferToSameAccountError, AccountNotFoundForTransferError, LowBalanceError;
}

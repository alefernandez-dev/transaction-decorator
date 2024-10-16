package dev.alejandro.transactionalDecorator.infra.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNTS")
public class AccountJpa implements Serializable {
    @Id
    UUID accountId;
    UUID clientId;
    BigDecimal balance;
}

CREATE TABLE IF NOT EXISTS accounts (
    account_id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    balance DECIMAL(19, 2) NOT NULL
);
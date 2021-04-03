package com.bruno.cqrs.exception;

import java.util.UUID;

public class AccountNotFoundException extends Throwable {
    public AccountNotFoundException(UUID id) {
        super("Account Not Found #" + id);
    }
}

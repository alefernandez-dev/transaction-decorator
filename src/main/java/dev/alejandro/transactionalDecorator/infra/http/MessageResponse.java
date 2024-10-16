package dev.alejandro.transactionalDecorator.infra.http;

import java.io.Serializable;

public record MessageResponse(String message, int code) implements Serializable {
}

package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ShoppingCartInactiveException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public ShoppingCartInactiveException(UUID shoppingCartId) {
        super("Корзина с ID " + shoppingCartId + " деактивирована.");
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.userMessage = "Корзина неактивна.";
    }
}

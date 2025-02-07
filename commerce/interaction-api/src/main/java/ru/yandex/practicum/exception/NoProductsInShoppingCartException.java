package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class NoProductsInShoppingCartException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public NoProductsInShoppingCartException(UUID productId) {
        super("Товара с ID: " + productId + " нет в корзине.");
        httpStatus = HttpStatus.BAD_REQUEST;
        userMessage = "Такого товара нет в корзине";
    }
}

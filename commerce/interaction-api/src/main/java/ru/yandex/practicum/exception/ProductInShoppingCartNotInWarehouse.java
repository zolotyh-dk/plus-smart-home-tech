package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ProductInShoppingCartNotInWarehouse extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public ProductInShoppingCartNotInWarehouse(UUID productId) {
        super("Товара с ID " + productId + " нет на складе.");
        this.httpStatus = HttpStatus.CONFLICT;
        this.userMessage = "Товара нет на складе.";
    }
}

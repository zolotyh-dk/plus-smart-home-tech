package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * Ошибка, товар из корзины не находится в требуемом количестве на складе
 */

public class ProductInShoppingCartLowQuantityInWarehouse extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public ProductInShoppingCartLowQuantityInWarehouse(UUID productId) {
        super("Товар из корзины с ID " + productId + " не находится в требуемом количестве на складе.");
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.userMessage = "Товар из корзины не находится в требуемом количестве на складе.";
    }
}

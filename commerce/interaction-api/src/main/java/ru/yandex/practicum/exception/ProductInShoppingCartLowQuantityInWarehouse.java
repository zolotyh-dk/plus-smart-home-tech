package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.UUID;

/**
 * Ошибка, товар из корзины не находится в требуемом количестве на складе
 */

public class ProductInShoppingCartLowQuantityInWarehouse extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public ProductInShoppingCartLowQuantityInWarehouse(Set<UUID> productIds) {
        super("Товары из корзины с ID " + productIds + " не находится в требуемом количестве на складе.");
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.userMessage = "Товары из корзины не находится в требуемом количестве на складе.";
    }
}

package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * Нет информации о товаре на складе
 */

public class NoSpecifiedProductInWarehouseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public NoSpecifiedProductInWarehouseException(UUID productId) {
        super("О товаре с ID " + productId + " нет информации на складе.");
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.userMessage = "О товаре нет информации на складе.";
    }
}

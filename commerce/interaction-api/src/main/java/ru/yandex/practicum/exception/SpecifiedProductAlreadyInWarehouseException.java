package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class SpecifiedProductAlreadyInWarehouseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public SpecifiedProductAlreadyInWarehouseException(UUID productId) {
        super("Товар с ID " + productId + " уже есть на складе.");
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.userMessage = "Такой товар уже есть на складе.";
    }
}

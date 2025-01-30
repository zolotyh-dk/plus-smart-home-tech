package ru.yandex.practicum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
public class ProductAlreadyExistsException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public ProductAlreadyExistsException(UUID productId) {
        super("Товар с ID " + productId + " уже существует.");
        this.httpStatus = HttpStatus.CONFLICT;
        this.userMessage = "Такой товар уже есть в магазине.";
    }
}
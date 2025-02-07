package ru.yandex.practicum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public ProductNotFoundException(UUID productId) {
        super("Товар с ID " + productId + " не найден.");
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.userMessage = "Товар не найден";
    }
}

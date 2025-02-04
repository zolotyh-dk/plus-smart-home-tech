package ru.yandex.practicum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
public class NotAuthorizedUserException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public NotAuthorizedUserException(String username) {
        super("Пользователь с именем " + username + " не найден.");
        httpStatus = HttpStatus.NOT_FOUND;
        userMessage = "Пользователь не найден";
    }
}

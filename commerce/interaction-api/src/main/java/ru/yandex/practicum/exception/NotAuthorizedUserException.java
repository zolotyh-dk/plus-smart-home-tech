package ru.yandex.practicum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotAuthorizedUserException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String userMessage;

    public NotAuthorizedUserException(String username) {
        super("Имя пользователя не должно быть пустым. Передано имя: " + "'" + username + "'");
        httpStatus = HttpStatus.UNAUTHORIZED;
        userMessage = "Имя пользователя не должно быть пустым";
    }
}

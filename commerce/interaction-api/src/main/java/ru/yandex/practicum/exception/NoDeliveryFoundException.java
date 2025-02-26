package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * Нет информации о доставке
 */

public class NoDeliveryFoundException extends ErrorResponse {
    public NoDeliveryFoundException(UUID deliveryId) {
        super(HttpStatus.BAD_REQUEST, "О доставке с ID " + deliveryId + " нет информации в системе.");
    }
}

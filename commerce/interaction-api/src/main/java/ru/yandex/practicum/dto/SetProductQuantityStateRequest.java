package ru.yandex.practicum.dto;

import java.util.UUID;

/**
 * Запрос на изменение статуса остатка товара
 *
 * @param productId         идентификатор товара
 * @param quantityState     статус, перечисляющий состояние остатка как свойства товара
 */
public record SetProductQuantityStateRequest(
        UUID productId,
        QuantityState quantityState
) {
}

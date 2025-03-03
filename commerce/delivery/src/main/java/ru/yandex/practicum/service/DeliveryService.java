package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.dto.order.OrderDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface DeliveryService {
    DeliveryDto createDelivery(DeliveryDto deliveryDto);

    void successDelivery(UUID orderId);

    void pickDelivery(UUID orderId);

    void failedDelivery(UUID orderId);

    BigDecimal calculateCost(OrderDto orderDto);
}

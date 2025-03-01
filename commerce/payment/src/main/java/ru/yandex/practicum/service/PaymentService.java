package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentService {
    PaymentDto createPayment(OrderDto orderDto);

    BigDecimal calculateTotalCost(OrderDto orderDto);

    void confirmPayment(UUID paymentId);

    BigDecimal calculateProductsCost(OrderDto orderDto);

    void failPayment(UUID paymentId);
}

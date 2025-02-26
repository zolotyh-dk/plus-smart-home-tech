package ru.yandex.practicum.dto.payment;

import lombok.Builder;

import java.util.UUID;

/**
 * Представление платежа в системе
 *
 * @param paymentId Идентификатор оплаты
 * @param totalPayment Общая стоимость
 * @param deliveryTotal Стоимость доставки
 * @param feeTotal Стоимость налога
 */
@Builder
public record PaymentDto (
        UUID paymentId,
        Double totalPayment,
        Double deliveryTotal,
        Double feeTotal
) {
}

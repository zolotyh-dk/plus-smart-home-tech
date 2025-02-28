package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.client.ShoppingStoreClient;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.exception.NotEnoughInfoInOrderToCalculateException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final BigDecimal FEE = BigDecimal.valueOf(0.1);
    private static final int PRICE_SCALE = 2;
    private final ShoppingStoreClient shoppingStoreClient;

    @Override
    public PaymentDto createPayment(OrderDto orderDto) {
        return null;
    }

    @Override
    public BigDecimal calculateTotalCost(OrderDto orderDto) {
        log.debug("Рассчитываем полную стоимость заказа с ID: {}", orderDto.orderId());

        if (orderDto.deliveryPrice() == null || orderDto.productPrice() == null) {
            throw new NotEnoughInfoInOrderToCalculateException(orderDto.orderId());
        }
        BigDecimal feePrice = orderDto.productPrice().multiply(FEE);
        log.debug("Рассчитали сумму НДС: {}", feePrice);
        return feePrice.add(orderDto.deliveryPrice())
                .add(orderDto.productPrice())
                .setScale(PRICE_SCALE, RoundingMode.HALF_EVEN);
    }

    @Override
    public void confirmPayment() {

    }

    @Override
    public BigDecimal calculateProductsCost(OrderDto orderDto) {
        log.debug("Рассчитываем стоимость товаров в заказе с ID: {}", orderDto.orderId());

        if (orderDto.products() == null || orderDto.products().isEmpty()) {
            log.warn("В заказе с ID: {} нет товаров", orderDto.orderId());
            return BigDecimal.ZERO;
        }

        BigDecimal productsCost = orderDto.products().entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    Long quantity = entry.getValue();
                    ProductDto productDto = shoppingStoreClient.getProductById(productId);
                    return productDto.price().multiply(new BigDecimal(quantity));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(PRICE_SCALE, RoundingMode.HALF_EVEN);
        log.debug("Рассчитали стоимость товаров: {}", productsCost);
        return productsCost;
    }

    @Override
    public void failPayment() {

    }
}

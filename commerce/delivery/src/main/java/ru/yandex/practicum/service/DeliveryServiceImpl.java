package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.client.OrderClient;
import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.dto.delivery.DeliveryState;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.exception.DeliveryForOrderAlreadyExistsException;
import ru.yandex.practicum.exception.NoDeliveryFoundException;
import ru.yandex.practicum.mapper.DeliveryMapper;
import ru.yandex.practicum.model.Delivery;
import ru.yandex.practicum.repository.DeliveryRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryMapper deliveryMapper;
    private final DeliveryRepository deliveryRepository;
    private final OrderClient orderClient;

    @Transactional
    @Override
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        log.debug("Сохраняем новую доставку для заказа с ID: {} в DB", deliveryDto.orderId());
        if (deliveryRepository.existsByOrderId(deliveryDto.orderId())) {
            throw new DeliveryForOrderAlreadyExistsException(deliveryDto.orderId());
        }
        Delivery delivery = deliveryMapper.toDelivery(deliveryDto);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        log.debug("СОхранили в DB новую доставку. ID доставки: {}. ID заказа: {}",
                savedDelivery.getId(), savedDelivery.getOrderId());
        return deliveryMapper.toDeliveryDto(savedDelivery);
    }

    @Transactional
    @Override
    public void successDelivery(UUID orderId) {
        log.debug("Устанавливаем признак успешной доставки для заказа с ID: {}", orderId);
        Delivery delivery = deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NoDeliveryFoundException(orderId));
        delivery.setState(DeliveryState.DELIVERED);
        orderClient.orderDelivery(orderId);
        log.debug("Установили признак успешной доставки. ID доставки: {}. ID заказа: {}", delivery.getId(), orderId);
    }

    @Override
    public void pickedDelivery(UUID orderId) {

    }

    @Override
    public void failedDelivery(UUID orderId) {
        log.debug("Устанавливаем признак неудачной доставки для заказа с ID: {}", orderId);
        Delivery delivery = deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NoDeliveryFoundException(orderId));
        delivery.setState(DeliveryState.FAILED);
        orderClient.orderDeliveryFailed(orderId);
        log.debug("Установили признак неудачной доставки. ID доставки: {}. ID заказа: {}", delivery.getId(), orderId);
    }

    @Override
    public BigDecimal calculateCost(OrderDto orderDto) {
        return null;
    }
}

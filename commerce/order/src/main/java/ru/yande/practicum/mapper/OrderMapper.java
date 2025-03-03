package ru.yande.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yande.practicum.model.Order;
import ru.yandex.practicum.dto.order.CreateNewOrderRequest;
import ru.yandex.practicum.dto.order.OrderDto;

import java.util.Collections;
import java.util.List;

@Component
public class OrderMapper {
    public OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }
        return OrderDto.builder()
                .orderId(order.getId())
                .shoppingCartId(order.getShoppingCartId())
                .products(order.getProducts())
                .paymentId(order.getPaymentId())
                .deliveryId(order.getDeliveryId())
                .state(order.getState())
                .deliveryWeight(order.getDeliveryWeight())
                .deliveryVolume(order.getDeliveryVolume())
                .fragile(order.getFragile())
                .totalPrice(order.getTotalPrice())
                .deliveryPrice(order.getDeliveryPrice())
                .productPrice(order.getProductPrice())
                .build();
    }

    public List<OrderDto> toDto(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return Collections.emptyList();
        }
        return orders.stream().map(this::toDto).toList();
    }

    public Order toOrder(String username, CreateNewOrderRequest request) {
        Order order = new Order();
        order.setShoppingCartId(request.shoppingCart().shoppingCartId());
        order.setUsername(username);
        order.setProducts(request.shoppingCart().products());
        return order;
    }
}

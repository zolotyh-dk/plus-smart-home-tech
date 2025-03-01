package ru.yandex.practicum.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.api.OrderOperations;
import ru.yandex.practicum.dto.order.OrderDto;

import java.util.UUID;

@FeignClient(name = "order")
public interface OrderClient extends OrderOperations {
    @Override
    @PostMapping("api/v1/payment")
    OrderDto orderPayment(@RequestBody UUID orderId);

    @Override
    @PostMapping("api/v1/payment/failed")
    OrderDto orderPaymentFailed(@RequestBody UUID orderId);
}

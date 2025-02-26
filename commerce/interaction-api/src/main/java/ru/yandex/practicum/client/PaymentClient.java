package ru.yandex.practicum.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.yandex.practicum.api.PaymentOperations;

@FeignClient(name = "payment")
public interface PaymentClient extends PaymentOperations {
}

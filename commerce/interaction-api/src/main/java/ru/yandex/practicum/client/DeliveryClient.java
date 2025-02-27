package ru.yandex.practicum.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.yandex.practicum.api.DeliveryOperations;

@FeignClient(name = "delivery")
public interface DeliveryClient extends DeliveryOperations {
}

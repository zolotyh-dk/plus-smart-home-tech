package ru.yandex.practicum.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.api.WarehouseOperations;
import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.ShippedToDeliveryRequest;

@FeignClient(name = "warehouse")
public interface WarehouseClient extends WarehouseOperations {
    @Override
    @PostMapping("/api/v1/warehouse/check")
    BookedProductsDto bookProducts(ShoppingCartDto shoppingCartDto);

    @Override
    @PostMapping("/api/v1/warehouse//shipped")
    void shipToDelivery(@RequestBody ShippedToDeliveryRequest request);
}

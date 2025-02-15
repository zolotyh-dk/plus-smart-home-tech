package ru.yandex.practicum.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;

@FeignClient(name = "warehouse")
public interface WarehouseClient extends WarehouseOperations {
    @Override
    @PostMapping("/api/v1/warehouse/check")
    BookedProductsDto bookProducts(ShoppingCartDto shoppingCartDto);
}

package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;

public interface WarehouseService {
    BookedProductsDto bookProducts(ShoppingCartDto shoppingCartDto);
}

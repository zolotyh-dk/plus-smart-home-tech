package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddProductToWarehouseRequest;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.dto.warehouse.NewProductInWarehouseRequest;

public interface WarehouseService {
    void addProduct(NewProductInWarehouseRequest request);

    BookedProductsDto check(ShoppingCartDto shoppingCartDto);

    void increaseQuantity(AddProductToWarehouseRequest request);

    AddressDto getAddress();
}

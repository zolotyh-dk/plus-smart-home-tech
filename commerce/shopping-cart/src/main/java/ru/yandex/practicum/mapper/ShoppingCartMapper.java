package ru.yandex.practicum.mapper;

import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.model.ShoppingCart;
import ru.yandex.practicum.model.ShoppingCartProduct;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShoppingCartMapper {
    public ShoppingCartDto toDto(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            return null;
        }
        Map<UUID, Long> products = shoppingCart.getProducts().stream()
                .collect(Collectors.toMap(ShoppingCartProduct::getProductId, ShoppingCartProduct::getQuantity));
        return ShoppingCartDto.builder()
                .shoppingCartId(shoppingCart.getId())
                .products(products)
                .build();
    }
}

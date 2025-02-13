package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ChangeProductQuantityRequest;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.service.ShoppingCartFacade;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController implements ShoppingCartOperations {
    private final ShoppingCartFacade facade;

    @Override
    public ShoppingCartDto getShoppingCart(String username) {
        log.info("GET /api/v1/shopping-cart - Получение корзины пользователя: {}", username);
        ShoppingCartDto response = facade.getShoppingCart(username);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public ShoppingCartDto addProductsToCart(String username, Map<UUID, Long> products) {
        log.info("POST /api/v1/shopping-cart - Добавление товаров в корзину пользователя: {}, товары: {}",
                username, products);
        ShoppingCartDto response = facade.addProductsToCart(username, products);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public void deactivateShoppingCart(String username) {
        log.info("DELETE /api/v1/shopping-cart - Деактивация корзины пользователя: {}", username);
        facade.deactivateShoppingCart(username);
        log.info("Корзина пользователя {} деактивирована.", username);
    }

    @Override
    public ShoppingCartDto replaceShoppingCartContents(String username, Map<UUID, Long> products) {
        log.info("PUT /api/v1/shopping-cart/remove - Замена содержимого корзины пользователя: {}, новые товары: {}",
                username, products);
        ShoppingCartDto response = facade.replaceShoppingCartContents(username, products);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public ShoppingCartDto changeProductQuantity(String username, ChangeProductQuantityRequest request) {
        log.info("PUT /api/v1/shopping-cart/change-quantity - " +
                 "Изменение количества товара в корзине пользователя: {}, запрос: {}", username, request);
        ShoppingCartDto response = facade.changeProductQuantity(username, request);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public BookedProductsDto bookProducts(String username) {
        log.info("POST /api/v1/shopping-cart/booking - Бронирование товаров для пользователя: {}", username);
        BookedProductsDto response = facade.bookProducts(username);
        log.info("Ответ: {}", response);
        return response;
    }
}

package ru.yandex.practicum.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "shopping-cart")
public interface ShoppingCartClient extends ShoppingCartOperations{
}

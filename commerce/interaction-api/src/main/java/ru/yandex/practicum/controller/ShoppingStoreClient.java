package ru.yandex.practicum.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "shopping-store")
public interface ShoppingStoreClient extends ShoppingStoreOperations {
}

package ru.yandex.practicum.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "warehouse")
public interface WarehouseClient extends WarehouseOperations{
}

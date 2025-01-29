package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.ProductCategory;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.dto.SetProductQuantityStateRequest;

import java.util.List;
import java.util.UUID;

public interface ShoppingStoreOperations {
    @GetMapping("/{productId}")
    ProductDto getProductById(@PathVariable UUID productId);

    @GetMapping
    List<ProductDto> getProducts(@RequestParam ProductCategory category,
                                 @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                 @RequestParam(defaultValue = "10") @Positive int size,
                                 @RequestParam List<String> sort);

    @PostMapping
    ProductDto addProduct(@RequestBody @Valid ProductDto productDto);

    @PutMapping
    ProductDto updateProduct(@RequestBody @Valid ProductDto productDto);

    @PutMapping("/quantityState")
    boolean updateQuantityState(@RequestBody @Valid SetProductQuantityStateRequest setProductQuantityStateRequest);

    @PutMapping("/removeProductFromStore")
    boolean removeProduct(@RequestParam @NotNull UUID productId);
}
